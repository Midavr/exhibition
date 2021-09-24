package ua.epam.radchenko.persistence.transaction;

import ua.epam.radchenko.persistence.connection.PooledConnection;
import ua.epam.radchenko.persistence.exepion.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Class responsible for transactions managing: begin, end, rollback processing
 */
class TransactionHelper implements AutoCloseable {
    Logger LOGGER = LoggerFactory.getLogger(Transaction.class);


    private final Connection connection = initConnection();
    private final int transactionIsolationLevel;

    private boolean isTransactionDoneWithoutRollback = true;
    private boolean autoCommitState;
    private int oldTransactionIsolation;

    public TransactionHelper(int transactionIsolationLevel) {
        this.transactionIsolationLevel = transactionIsolationLevel;
    }

    public boolean isTransactionDoneWithoutRollback() {
        return isTransactionDoneWithoutRollback;
    }

    void prepareConnectionForTransaction() {
        try {
            autoCommitState = connection.getAutoCommit();
            connection.setAutoCommit(false);
            oldTransactionIsolation = connection.getTransactionIsolation();
            if (oldTransactionIsolation != transactionIsolationLevel)
                connection.setTransactionIsolation(transactionIsolationLevel);

            LOGGER.debug("Changed connection autoCommit state to false");
        } catch (SQLException prepareException) {
            throw new TransactionException(prepareException);
        }
    }

    void commit() {
        if (isTransactionDoneWithoutRollback) {
            try {
                connection.commit();
                LOGGER.debug("Commit successfully done");
            } catch (SQLException commitException) {
                throw new TransactionException(commitException);
            }
        }
    }

    void doRollback() throws SQLException {
        connection.rollback();
        isTransactionDoneWithoutRollback = false;
        LOGGER.debug("Rollback successfully done");
    }

    void returnDefaultConnectionState() {
        try {
            connection.setAutoCommit(autoCommitState);
            if (connection.getTransactionIsolation() != oldTransactionIsolation)
                connection.setTransactionIsolation(oldTransactionIsolation);

            LOGGER.debug("Changed connection state to default");
        } catch (SQLException afterCommitException) {
            throw new TransactionException(afterCommitException);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Can't close connection", e);
        }
    }

    private Connection initConnection() {
        Connection connection = PooledConnection.getInstance().getConnection();
        if (Objects.nonNull(connection)) {
            LOGGER.debug("Got connection");
            return connection;
        } else {
            throw new TransactionException("Connection equals null");
        }
    }
}

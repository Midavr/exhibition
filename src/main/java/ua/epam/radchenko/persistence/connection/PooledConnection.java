package ua.epam.radchenko.persistence.connection;

import ua.epam.radchenko.persistence.exepion.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool that could be initialized by root or custom data source
 */
public class PooledConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(PooledConnection.class);

    private static final PooledConnection INSTANCE = new PooledConnection();
    private static final DataSource DATA_SOURCE;

    static {
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup(
                    "java:/comp/env/jdbc/exhibition");
            DATA_SOURCE = new DataSourceProxy(ds);
        } catch (NamingException e) {
            LOGGER.error("Exception initialize DataSource", e);
            throw new ConnectionException(e);
        }
    }

    private PooledConnection() {
    }

    public static PooledConnection getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }
}

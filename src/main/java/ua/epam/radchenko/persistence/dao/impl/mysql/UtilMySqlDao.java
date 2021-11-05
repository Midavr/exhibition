package ua.epam.radchenko.persistence.dao.impl.mysql;

import ua.epam.radchenko.persistence.connection.PooledConnection;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.EntityMapper;
import ua.epam.radchenko.persistence.exepion.DaoException;
import ua.epam.radchenko.util.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UtilMySqlDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilMySqlDao.class);

    /**
     * Converts data from ResultSet to domain object
     */
    private EntityMapper<T> mapper;

    public UtilMySqlDao(EntityMapper<T> mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves one object from database which matches given query.
     *
     * @param query  raw sql syntax to select object. Can contains ? wildcard.
     * @param params parameters to substitute wildcards in query
     * @return Optional object, which contains retrieved object or null
     */
    public Optional<T> findOne(String query, Object... params) {
        List<T> results = findAll(query + " LIMIT 1", params);
        return Optional.ofNullable(results.isEmpty() ? null : results.get(0));
    }

    /**
     * Retrieves all objects from database which match given query.
     *
     * @param query  raw sql syntax for objects selecting. Can contains ? wildcard.
     * @param params parameters to substitute wildcards in query
     * @return list of retrieved objects
     */
    public List<T> findAll(String query, Object... params) {

        try (Connection connection = PooledConnection.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(query)) {
            setParamsToStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            return mapper.mapToObjectList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Failed to execute query", e);
            throw new DaoException(e);
        }
    }

    /**
     * Performs update of some table in database
     * based on given query and parameters.
     *
     * @param query  sql-based string, which specify update behavior
     * @param params parameters to substitute wildcards in query
     */
    public void executeUpdate(String query, Object... params) {
        try (Connection connection = PooledConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParamsToStatement(statement, params);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to execute query", e);
            throw new DaoException(e);
        }
    }

    /**
     * Performs insertion for entities with generated primary key field.
     * For entities, which doesn't have auto-generated fields -
     * use {@link #executeUpdate(String, Object...)} method
     * to properly persist data.
     *
     * @param query  sql-based string, which specify details of insertion operation
     * @param params parameters to substitute wildcards in query
     * @param pkType entity primary key type
     * @return generated id
     */
    public <PK> PK executeInsertWithGeneratedPrimaryKey(String query,
                                                        Class<PK> pkType,
                                                        Object... params) {
        try (Connection connection = PooledConnection.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setParamsToStatement(statement, params);
            statement.executeUpdate();

            return getGeneratedPrimaryKey(statement, pkType);
        } catch (SQLException e) {
            LOGGER.error("Failed to execute query", e);
            throw new DaoException(e);
        }
    }

    public int executeInsertWithGeneratedPrimaryKey(String query,
                                                     Object... params) {
        return executeInsertWithGeneratedPrimaryKey(query, Integer.class, params);
    }

    /**
     * Retrieves count of objects from database which match given query.
     *
     * @param query sql-based string, which specify details of counting operation
     * @return count of rows for query
     */
    public long getRowsCount(String query, Object... params) {
        try (Connection connection = PooledConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setParamsToStatement(statement, params);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new SQLException("Can't retrieve count of objects");
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to execute query", e);
            throw new DaoException(e);
        }
    }

    /**
     * Sets all parameters to statement.
     *
     * @param statement PreparedStatement
     * @param params    parameters to substitute wildcards in raw query
     * @throws SQLException SQLException
     */
    private void setParamsToStatement(PreparedStatement statement, Object... params)
            throws SQLException {
        if (Objects.isNull(params)) {
            throw new SQLException("Params cannot be null");
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                statement.setObject(i + 1, params[i]);
            } else {
                statement.setNull(i + 1, Types.OTHER);
            }
        }
    }

    /**
     * Get from resultSet generated by database primary key.
     * Use only after execution statement.
     * Statement must be in Statement.RETURN_GENERATED_KEYS mode.
     *
     * @param statement PreparedStatement
     * @param pkType    entity primary key type
     * @return generated key
     * @throws SQLException if statement doesn't generates key
     */
    private <PK> PK getGeneratedPrimaryKey(PreparedStatement statement, Class<PK> pkType)
            throws SQLException {
        if (Objects.isNull(pkType)) {
            throw new SQLException("Primary key type is null");
        }

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            if (pkType.isAssignableFrom(Integer.class)) {
                Integer key = rs.getInt(1);
                return pkType.cast(key);
            } else if (pkType.isAssignableFrom(Long.class)) {
                Long key = rs.getLong(1);
                return pkType.cast(key);
            } else {
                throw new SQLException("Unsupported key type");
            }
        } else {
            throw new SQLException("Can't retrieve generated key");
        }
    }

}


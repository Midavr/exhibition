package ua.epam.radchenko.persistence.exepion;

/**
 * Artificial exception that should be thrown out of the DAO layer
 */
public class DaoException extends ConnectionException {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
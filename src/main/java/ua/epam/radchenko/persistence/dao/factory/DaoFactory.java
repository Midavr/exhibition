package ua.epam.radchenko.persistence.dao.factory;

import ua.epam.radchenko.persistence.dao.*;
import ua.epam.radchenko.persistence.exepion.DaoException;
import ua.epam.radchenko.util.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory that creates DAO entities
 */
public abstract class DaoFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class);
    private static final String DB_CLASS = "factory.class";

    private static volatile DaoFactory instance;

    /**
     * Gets factory class name from certain properties file.
     * Reflection used for more flexibility.
     *
     * @return specific implemented factory
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    try {
                        String className = ResourceManager.DATABASE.getProperty(DB_CLASS);
                        instance = (DaoFactory) Class.forName(className)
                                .getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        LOGGER.error("Failed to initialize DaoFactory", e);
                        throw new DaoException(e);
                    }
                }
            }
        }
        return instance;
    }

    public abstract ExhibitionDao getExhibitionDao();

    public abstract OrderDao getOrderDao();

    public abstract UserDao getUserDao();
}

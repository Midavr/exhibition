package ua.epam.radchenko.persistence.dao;

import ua.epam.radchenko.persistence.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User, Integer> {

    /**
     * Retrieve user from database identified by email.
     * @param login identifier of user
     * @return optional, which contains retrieved object or {@code null}
     */
    Optional<User> findOneByLogin(String login);

    /**
     * Check if user exists in database.
     *
     * @param login user's identifier
     * @return {@code true} if exists else {@code false}
     */
    boolean existByLogin(String login);
}

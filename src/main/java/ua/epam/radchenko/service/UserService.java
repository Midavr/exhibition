package ua.epam.radchenko.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.radchenko.persistence.dao.UserDao;
import ua.epam.radchenko.persistence.dao.factory.DaoFactory;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.util.PasswordManager;
import ua.epam.radchenko.util.type.Role;

import java.util.Objects;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing user-related operations
 */
public class UserService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserService.class);
    private final Role defaultRole = Role.USER;
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    private UserService() {
    }

    private static class Singleton {
        private final static UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Singleton.INSTANCE;
    }

    public Optional<User> signIn(String login, String password) {
        LOGGER.debug("Attempt to sign in");
        if (Objects.isNull(login) || Objects.isNull(password)) {
            return Optional.empty();
        }
        Optional<User> user = userDao.findOneByLogin(login);
        return user
                .filter(u -> PasswordManager.checkSecurePassword(
                        password, u.getPassword()))
                .map(u -> {
                    u.setPassword(null);
                    return u;
                });
    }

    public Optional<User> findUserById(int id) {
        LOGGER.debug("Attempt to find user by id");
        return userDao.findOne(id)
                .map(user -> {
                    user.setPassword(null);
                    return user;
                });
    }

    public boolean registerUser(User userToRegister) {
        LOGGER.debug("Attempt to register new user");
        if (Objects.isNull(userToRegister.getLogin()) ||
                Objects.isNull(userToRegister.getPassword())) {
            return false;
        }
        if (userToRegister.getRole() == null) {
            userToRegister.setRole(defaultRole);
        }
        boolean userIsPresent = userDao.existByLogin(userToRegister.getLogin());
        if (!userIsPresent) {
            String hash = PasswordManager.hashPassword(
                    userToRegister.getPassword());
            userToRegister.setPassword(hash);
            userDao.insert(userToRegister);
            return true;
        }
        return false;
    }
}

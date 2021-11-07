package ua.epam.radchenko.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.epam.radchenko.persistence.dao.UserDao;
import ua.epam.radchenko.persistence.dao.factory.DaoFactory;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.service.UserService;
import ua.epam.radchenko.util.PasswordManager;
import ua.epam.radchenko.util.type.Role;

import java.util.Objects;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing user-related operations
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserServiceImpl.class);
    private final Role defaultRole = Role.USER;
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
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

    @Override
    public Optional<User> findUserById(int id) {
        LOGGER.debug("Attempt to find user by id");
        return userDao.findOne(id)
                .map(user -> {
                    user.setPassword(null);
                    return user;
                });
    }

    @Override
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

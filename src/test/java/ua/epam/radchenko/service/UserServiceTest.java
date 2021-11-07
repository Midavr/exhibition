package ua.epam.radchenko.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.dao.UserDao;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.util.PasswordManager;
import ua.epam.radchenko.util.type.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final UserService userService = context.getBean("userServiceImpl", UserService.class);
    @Mock
    private UserDao userDao;
    private final String login = "user";
    private final String password = "password";
    private final String hashPassword = PasswordManager.hashPassword(password);

    @Test
    void findUserByIdWithExistingUserTest() {
        int userId = 1;
        Optional<User> expected = Optional.of(
                User.newBuilder()
                        .setUserId(userId)
                        .build());
        when(userDao.findOne(userId)).thenReturn(expected);

        Optional<User> actual = userService.findUserById(userId);

        assertEquals(expected, actual);
        verify(userDao, times(1)).findOne(userId);
    }

    @Test
    void findUserByIdWithNotExistingUserTest() {
        int userId = 1;
        when(userDao.findOne(userId)).thenReturn(Optional.empty());

        Optional<User> periodicalOpt = userService.findUserById(userId);

        assertFalse(periodicalOpt.isPresent());
        verify(userDao, times(1)).findOne(userId);
    }

    @Test
    void signInWithCorrectLoginAndPasswordTest() {
        Optional<User> expected = Optional.of(User.newBuilder()
                .setLogin(login)
                .setPassword(hashPassword)
                .build());
        when(userDao.findOneByLogin(login)).thenReturn(expected);

        Optional<User> actual = userService.signIn(login, password);

        assertEquals(expected, actual);
        verify(userDao, times(1)).findOneByLogin(login);
    }

    @Test
    void signInWithWrongPasswordTest() {
        Optional<User> findByLoginUser = Optional.of(User.newBuilder()
                .setLogin(login)
                .setPassword(hashPassword)
                .build());
        when(userDao.findOneByLogin(login)).thenReturn(findByLoginUser);

        Optional<User> actual = userService.signIn(login, "another password");

        assertFalse(actual.isPresent());
        verify(userDao, times(1)).findOneByLogin(login);
    }

    @Test
    void signInWithWrongLoginTest() {
        when(userDao.findOneByLogin(login)).thenReturn(Optional.empty());

        Optional<User> actual = userService.signIn(login, password);

        assertFalse(actual.isPresent());
        verify(userDao, times(1)).findOneByLogin(login);
    }

    @Test
    void signInWithNullLoginTest() {
        Optional<User> actual = userService.signIn(null, password);

        assertFalse(actual.isPresent());
        verify(userDao, never()).findOneByLogin(login);
    }

    @Test
    void signInWithNullPasswordTest() {
        Optional<User> actual = userService.signIn(login, null);

        assertFalse(actual.isPresent());
        verify(userDao, never()).findOneByLogin(login);
    }

    @Test
    void registerUserWithValidParametersTest() {
        User user = User.newBuilder()
                .setLogin(login)
                .setPassword(password)
                .build();
        when(userDao.existByLogin(login)).thenReturn(false);

        assertTrue(userService.registerUser(user));
        assertEquals(Role.USER, user.getRole());
        assertEquals(hashPassword, user.getPassword());
        verify(userDao, times(1)).insert(any(User.class));
    }

    @Test
    void registerUserWithSameLoginTest() {
        User user = User.newBuilder()
                .setLogin(login)
                .setPassword(password)
                .build();
        when(userDao.existByLogin(login)).thenReturn(true);

        assertFalse(userService.registerUser(user));
        verify(userDao, never()).insert(any(User.class));
    }

    @Test
    void registerUserWithNullLoginTest() {
        User user = User.newBuilder()
                .setLogin(null)
                .setPassword(password)
                .build();

        assertFalse(userService.registerUser(user));
        verify(userDao, never()).insert(any(User.class));
    }

    @Test
    void registerUserWithNullPasswordTest() {
        User user = User.newBuilder()
                .setLogin(login)
                .setPassword(null)
                .build();

        assertFalse(userService.registerUser(user));
        verify(userDao, never()).insert(any(User.class));
    }
}
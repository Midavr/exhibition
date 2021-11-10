package ua.epam.radchenko.service;

import ua.epam.radchenko.persistence.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> signIn(String login, String password);

    Optional<User> findUserById(int id);

    boolean registerUser(User userToRegister);


}

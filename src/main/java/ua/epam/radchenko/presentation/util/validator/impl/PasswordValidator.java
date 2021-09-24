package ua.epam.radchenko.presentation.util.validator.impl;

import ua.epam.radchenko.presentation.util.validator.Validator;

import java.util.Objects;

public class PasswordValidator implements Validator<String> {
    private static final int PASSWORD_MIN_LENGTH = 5;
    private static final int PASSWORD_MAX_LENGTH = 255;

    @Override
    public boolean isValid(String password) {
        return Objects.nonNull(password) &&
                password.length() >= PASSWORD_MIN_LENGTH &&
                password.length() <= PASSWORD_MAX_LENGTH;
    }
}

package ua.epam.radchenko.presentation.util.validator.impl;

import ua.epam.radchenko.presentation.util.validator.Validator;

import java.util.Objects;

public class LoginValidator implements Validator<String> {
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 255;

    /**
     * Regex used to perform validation of data.
     */
    @Override
    public boolean isValid(String password) {
        return Objects.nonNull(password) &&
                password.length() >= MIN_LENGTH &&
                password.length() <= MAX_LENGTH;
    }
}

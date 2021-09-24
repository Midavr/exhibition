package ua.epam.radchenko.presentation.util.validator.impl;

public class UserNameValidator extends RegexValidator {
    private static final int MAX_LENGTH = 255;

    /**
     * Regex used to perform validation of name.
     */
    private static final String USER_NAME_REGEX = "^\\p{Lu}[\\p{L}&&[^\\p{Lu}]]+$";

    public UserNameValidator() {
        super(USER_NAME_REGEX, MAX_LENGTH);
    }
}

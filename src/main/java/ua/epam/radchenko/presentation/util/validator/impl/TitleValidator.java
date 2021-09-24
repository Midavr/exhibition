package ua.epam.radchenko.presentation.util.validator.impl;

public class TitleValidator extends RegexValidator {
    private static final int MAX_LENGTH = 255;

    /**
     * Regex used to perform validation of data.
     */
    private static final String TITLE_REGEX = "^[\\p{L}\\p{Digit}\\p{javaWhitespace}]+$";

    public TitleValidator() {
        super(TITLE_REGEX, MAX_LENGTH);
    }
}

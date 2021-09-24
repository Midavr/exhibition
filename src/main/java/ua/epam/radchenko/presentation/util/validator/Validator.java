package ua.epam.radchenko.presentation.util.validator;

public interface Validator<T> {
    /**
     * Check is input message is valid
     *
     * @param obj that need to check
     * @return {@code true} if input is valid
     * {@code false} if input is not valid
     */
    boolean isValid(T obj);
}

package ua.epam.radchenko.presentation.util.validator;

import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.command.impl.authorization.PostSignUpCommand;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.validator.impl.LoginValidator;
import ua.epam.radchenko.presentation.util.validator.impl.PasswordValidator;
import ua.epam.radchenko.presentation.util.validator.impl.UserNameValidator;

import java.io.Serializable;
import java.util.Map;

/**
 * Validation data in {@link PostSignUpCommand#execute}
 */
public class SignUpValidatorManager extends ValidatorManager {
    @Override
    protected void validateObject(Map<String, Boolean> errors, Serializable object) {
        if (!(object instanceof User)) {
            throw new IllegalArgumentException("The object is not a type of User");
        }
        User user = (User) object;
        validateField(new LoginValidator(),
                user.getLogin(),
                Attributes.ERROR_LOGIN,
                errors);
        validateField(new PasswordValidator(),
                user.getPassword(),
                Attributes.ERROR_PASSWORD,
                errors);
        validateField(new UserNameValidator(),
                user.getFirstName(),
                Attributes.ERROR_FIRST_NAME,
                errors);
        validateField(new UserNameValidator(),
                user.getLastName(),
                Attributes.ERROR_LAST_NAME,
                errors);
    }
}

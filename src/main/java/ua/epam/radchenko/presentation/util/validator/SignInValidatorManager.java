package ua.epam.radchenko.presentation.util.validator;

import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.command.impl.authorization.PostSignInCommand;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.validator.impl.LoginValidator;
import ua.epam.radchenko.presentation.util.validator.impl.PasswordValidator;

import java.io.Serializable;
import java.util.Map;

/**
 * Validation data in {@link PostSignInCommand#execute}
 */
public class SignInValidatorManager extends ValidatorManager {

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
    }

}

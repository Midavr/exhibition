package ua.epam.radchenko.presentation.util.validator;

public class ValidatorManagerFactory {
    public static ValidatorManager getSignInValidator() {
        return new SignInValidatorManager();
    }

    public static ValidatorManager getSignUpValidator() {
        return new SignUpValidatorManager();
    }

    public static ValidatorManager getExhibitionValidator() {
        return new ExhibitionValidatorManager();
    }

}

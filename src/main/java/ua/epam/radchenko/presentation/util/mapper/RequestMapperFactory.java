package ua.epam.radchenko.presentation.util.mapper;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.User;

public class RequestMapperFactory {
    private static final RequestEntityMapper<User> SIGN_IN_MAPPER =
            new SignInRequestMapper();
    private static final RequestEntityMapper<User> SIGN_UP_MAPPER =
            new SignUpRequestMapper();
    private static final RequestEntityMapper<Exhibition> CREATE_EXHIBITION_MAPPER =
            new CreateExhibitionRequestMapper();

    public static RequestEntityMapper<User> getSignInMapper() {
        return SIGN_IN_MAPPER;
    }

    public static RequestEntityMapper<User> getSignUpMapper() {
        return SIGN_UP_MAPPER;
    }

    public static RequestEntityMapper<Exhibition> getCreateExhibitionMapper() {
        return CREATE_EXHIBITION_MAPPER;
    }
}

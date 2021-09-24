package ua.epam.radchenko.presentation.util.mapper;

import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;

public class SignInRequestMapper implements RequestEntityMapper<User> {

        @Override
        public User mapToObject(HttpServletRequest request) {
            return User.newBuilder()
                    .setLogin(request.getParameter(RequestParameters.USER_LOGIN))
                    .setPassword(request.getParameter(RequestParameters.USER_PASSWORD))
                    .build();
        }
}

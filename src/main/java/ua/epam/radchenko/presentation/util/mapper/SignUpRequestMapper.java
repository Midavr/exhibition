package ua.epam.radchenko.presentation.util.mapper;

import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;
import ua.epam.radchenko.util.type.Gender;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class SignUpRequestMapper
        implements RequestEntityMapper<User> {

    @Override
    public User mapToObject(HttpServletRequest request) {
        return User.newBuilder()
                .setLogin(request.getParameter(RequestParameters.USER_LOGIN))
                .setPassword(request.getParameter(RequestParameters.USER_PASSWORD))
                .setFirstName(request.getParameter(RequestParameters.USER_FIRST_NAME))
                .setLastName(request.getParameter(RequestParameters.USER_LAST_NAME))
                .setGender(
                        Gender.valueOf(request.getParameter(
                                RequestParameters.USER_GENDER).toUpperCase()))
                .setDateOfBirth(LocalDate.parse(
                        request.getParameter(RequestParameters.USER_DATE_OF_BIRTH)))
                .build();
    }
}

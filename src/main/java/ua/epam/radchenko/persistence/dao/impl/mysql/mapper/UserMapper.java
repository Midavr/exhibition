package ua.epam.radchenko.persistence.dao.impl.mysql.mapper;

import ua.epam.radchenko.persistence.entity.User;

import ua.epam.radchenko.util.type.Gender;
import ua.epam.radchenko.util.type.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements EntityMapper<User> {
    private static final String USER_ID_FIELD = "user_id";
    private static final String LOGIN_FIELD = "login";
    private static final String PASSWORD_FIELD = "password";
    private static final String ROLE_FIELD = "role";
    private static final String FIRST_NAME_FIELD = "first_name";
    private static final String LAST_NAME_FIELD = "last_name";
    private static final String DATE_OF_BIRTH_FIELD = "date_of_birth";
    private static final String GENDER_FIELD = "gender";

    @Override
    public User mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        return User.newBuilder()
                .setUserId(resultSet.getInt(tablePrefix + USER_ID_FIELD))
                .setLogin(resultSet.getString(tablePrefix + LOGIN_FIELD))
                .setPassword(resultSet.getString(tablePrefix + PASSWORD_FIELD))
                .setRole(Role.valueOf(resultSet.getString(tablePrefix + ROLE_FIELD)))
                .setFirstName(resultSet.getString(tablePrefix + FIRST_NAME_FIELD))
                .setLastName(resultSet.getString(tablePrefix + LAST_NAME_FIELD))
                .setDateOfBirth(resultSet.getObject(
                        tablePrefix + DATE_OF_BIRTH_FIELD, LocalDate.class))
                .setGender(Gender.valueOf(resultSet.getString(tablePrefix + GENDER_FIELD)))
                .build();
    }
}

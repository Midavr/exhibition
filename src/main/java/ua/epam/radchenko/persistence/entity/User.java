package ua.epam.radchenko.persistence.entity;

import ua.epam.radchenko.util.type.Gender;
import ua.epam.radchenko.util.type.Role;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class that represents user of system;
 */
public class User implements Serializable {
    private Integer userId;
    private String login;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;

    public static class Builder {
        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder setUserId(Integer userId) {
            user.setUserId(userId);
            return this;
        }

        public Builder setLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public Builder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            user.setDateOfBirth(dateOfBirth);
            return this;
        }

        public Builder setGender(Gender gender) {
            user.setGender(gender);
            return this;
        }

        public User build() {
            return user;
        }
    }

    public static User.Builder newBuilder() {return new Builder();}

    public User(){}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isAdmin() {
        return Role.ADMIN.equals(role);
    }

    public boolean isUser() {
        return Role.USER.equals(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }
}

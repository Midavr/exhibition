package ua.epam.radchenko.presentation.command.impl.authorization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.UserService;
import ua.epam.radchenko.util.type.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostSignUpCommandTest {
    @InjectMocks
    private PostSignInCommand postSignInCommand;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private final String login = "user";
    private final String password = "WARWAR";

    @Test
    void executeForAdminCredentialsTest() {
        CommandResult expected = CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
        User admin = User.newBuilder()
                .setLogin(login)
                .setPassword(password)
                .setRole(Role.ADMIN)
                .build();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(RequestParameters.USER_LOGIN)).thenReturn(login);
        when(request.getParameter(RequestParameters.USER_PASSWORD)).thenReturn(password);
        when(userService.signIn(login, password)).thenReturn(Optional.of(admin));

        CommandResult actual = postSignInCommand.execute(request, response);

        assertEquals(expected, actual);
        verify(userService, times(1)).signIn(login, password);
        verify(session, times(1)).setAttribute(Attributes.USER, admin);
    }

    @Test
    void executeForUserCredentialsTest() {
        CommandResult expected = CommandResult.redirect(PagesPaths.CATALOG_PATH);
        User admin = User.newBuilder()
                .setLogin(login)
                .setPassword(password)
                .setRole(Role.USER)
                .build();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(RequestParameters.USER_LOGIN)).thenReturn(login);
        when(request.getParameter(RequestParameters.USER_PASSWORD)).thenReturn(password);
        when(userService.signIn(login, password)).thenReturn(Optional.of(admin));

        CommandResult actual = postSignInCommand.execute(request, response);

        assertEquals(expected, actual);
        verify(userService, times(1)).signIn(login, password);
        verify(session, times(1)).setAttribute(Attributes.USER, admin);
    }

    @Test
    void executeForInvalidCredentialsTest() {
        CommandResult expected = CommandResult.forward(Views.SIGN_IN_VIEW);
        User invalidUser = User.newBuilder()
                .setLogin(login)
                .setPassword(password)
                .build();
        Map<String, Boolean> errors = new HashMap<>();
        errors.put(Attributes.ERROR_AUTHENTICATION, true);
        when(request.getParameter(RequestParameters.USER_LOGIN)).thenReturn(login);
        when(request.getParameter(RequestParameters.USER_PASSWORD)).thenReturn(password);
        when(userService.signIn(login, password)).thenReturn(Optional.empty());

        CommandResult actual = postSignInCommand.execute(request, response);

        assertEquals(expected, actual);
        verify(userService, times(1)).signIn(login, password);
        verify(session, never()).setAttribute(anyString(), any(User.class));
        verify(request, times(1)).setAttribute(Attributes.ERRORS, errors);
        verify(request, times(1)).setAttribute(Attributes.USER_DTO, invalidUser);
    }

    @Test
    void executeForInvalidLoginParameterTest() {
        CommandResult expected = CommandResult.forward(Views.SIGN_IN_VIEW);
        User invalidUser = User.newBuilder()
                .setLogin(login)
                .setPassword(password)
                .build();
        Map<String, Boolean> errors = new HashMap<>();
        errors.put(Attributes.ERROR_LOGIN, true);
        when(request.getParameter(RequestParameters.USER_LOGIN)).thenReturn(null);
        when(request.getParameter(RequestParameters.USER_PASSWORD)).thenReturn(password);

        CommandResult actual = postSignInCommand.execute(request, response);

        assertEquals(expected, actual);
        verify(userService, never()).signIn(login, password);
        verify(session, never()).setAttribute(anyString(), any(User.class));
        verify(request, times(1)).setAttribute(Attributes.ERRORS, errors);
        verify(request, times(1)).setAttribute(Attributes.USER_DTO, invalidUser);
    }

    @Test
    void executeForInvalidPasswordParameterTest() {
        CommandResult expected = CommandResult.forward(Views.SIGN_IN_VIEW);
        User invalidUser = User.newBuilder()
                .setLogin(login)
                .setPassword(null)
                .build();
        Map<String, Boolean> errors = new HashMap<>();
        errors.put(Attributes.ERROR_PASSWORD, true);
        when(request.getParameter(RequestParameters.USER_LOGIN)).thenReturn(login);
        when(request.getParameter(RequestParameters.USER_PASSWORD)).thenReturn(null);

        CommandResult actual = postSignInCommand.execute(request, response);

        assertEquals(expected, actual);
        verify(userService, never()).signIn(login, password);
        verify(session, never()).setAttribute(anyString(), any(User.class));
        verify(request, times(1)).setAttribute(Attributes.ERRORS, errors);
        verify(request, times(1)).setAttribute(Attributes.USER_DTO, invalidUser);
    }
}
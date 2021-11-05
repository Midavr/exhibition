package ua.epam.radchenko.presentation.command.impl.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.presentation.util.mapper.RequestMapperFactory;
import ua.epam.radchenko.presentation.util.validator.ValidatorManagerFactory;
import ua.epam.radchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PostSignUpCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostSignUpCommand.class);
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final UserService userService = context.getBean("userService", UserService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start of new user registration");
        User userDTO = RequestMapperFactory.getSignUpMapper()
                .mapToObject(request);

        Map<String, Boolean> errors = ValidatorManagerFactory.getSignUpValidator()
                .validate(userDTO);

        if (errors.isEmpty()) {
            boolean isRegistered = userService.registerUser(userDTO);
            if (isRegistered) {
                LOGGER.debug("User was successfully register");
                return CommandResult.redirect(PagesPaths.SIGN_IN_PATH);
            } else {
                LOGGER.debug("Such user already registered");
                errors.put(Attributes.ERROR_REGISTRATION, true);
            }
        } else {
            LOGGER.debug("Invalid registration parameters");
        }
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.USER_DTO, userDTO);
        LOGGER.debug("User registration fail");
        return CommandResult.forward(Views.SIGN_UP_VIEW);
    }
}
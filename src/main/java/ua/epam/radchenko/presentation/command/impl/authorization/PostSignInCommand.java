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
import java.util.Optional;

public class PostSignInCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostSignInCommand.class);
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final UserService userServiceImpl = context.getBean("userServiceImpl", UserService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start of sign in process");
        User userDTO = RequestMapperFactory.getSignInMapper()
                .mapToObject(request);

        Map<String, Boolean> errors = ValidatorManagerFactory.getSignInValidator()
                .validate(userDTO);

        if (errors.isEmpty()) {
            LOGGER.debug("Try to sign in");
            Optional<User> userOpt =
                    userServiceImpl.signIn(userDTO.getLogin(), userDTO.getPassword());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                request.getSession().setAttribute(Attributes.USER, user);
                LOGGER.debug("User successfully signed in");
                if (user.isAdmin()) {
                    return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
                } else{
                    return CommandResult.redirect(PagesPaths.CATALOG_PATH);
                }
            } else {
                LOGGER.debug("Email and password don't matches");
                errors.put(Attributes.ERROR_AUTHENTICATION, true);
            }
        } else {
            LOGGER.debug("Invalid authentication parameters");
        }
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.USER_DTO, userDTO);
        LOGGER.debug("User fail sign in");
        return CommandResult.forward(Views.SIGN_IN_VIEW);
    }
}

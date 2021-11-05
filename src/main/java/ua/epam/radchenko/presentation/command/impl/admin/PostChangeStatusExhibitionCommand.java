package ua.epam.radchenko.presentation.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.Util;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.util.type.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PostChangeStatusExhibitionCommand implements Command {
    private static Logger LOGGER =
            LoggerFactory.getLogger(PostChangeStatusExhibitionCommand.class);
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final ExhibitionService exhibitionService = context.getBean("exhibitionService", ExhibitionService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start the process of changing status of the exhibition");
        String referer = Util.getReferer(request, PagesPaths.ADMIN_CATALOG_PATH);
        int exhibitionId = Integer.parseInt(
                request.getParameter(RequestParameters.EXHIBITION_ID));
        Status status = Status.valueOf(
                request.getParameter(RequestParameters.EXHIBITION_STATUS).toUpperCase());
        Optional<Exhibition> exhibitionOpt =
                exhibitionService.findExhibitionById(exhibitionId);
        if (exhibitionOpt.isPresent()) {
            exhibitionService.changeStatus(exhibitionOpt.get(), status);
            LOGGER.debug("Status of the exhibition was successfully changed");
        } else {
            LOGGER.debug("exhibition with id {} doesn't exist. " +
                    "Changing status of the exhibition failed", exhibitionId);
        }
        return CommandResult.redirect(referer);
    }
}

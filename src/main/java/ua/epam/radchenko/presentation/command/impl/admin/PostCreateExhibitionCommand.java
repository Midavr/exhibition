package ua.epam.radchenko.presentation.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.presentation.util.mapper.RequestMapperFactory;
import ua.epam.radchenko.presentation.util.validator.ValidatorManagerFactory;
import ua.epam.radchenko.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PostCreateExhibitionCommand implements Command {
    private static Logger LOGGER =
            LoggerFactory.getLogger(PostCreateExhibitionCommand.class);

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final ExhibitionService exhibitionService = context.getBean("exhibitionService", ExhibitionService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start of new exhibition creation");
        Exhibition exhibitionDTO = RequestMapperFactory.getCreateExhibitionMapper()
                .mapToObject(request);
        Map<String, Boolean> errors = ValidatorManagerFactory.getExhibitionValidator()
                .validate(exhibitionDTO);
        if (errors.isEmpty()) {
            exhibitionService.createExhibition(exhibitionDTO);
            LOGGER.debug("Exhibition was successfully create");
            return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
        }

        LOGGER.debug("Invalid creation parameters");
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.EXHIBITION_DTO, exhibitionDTO);

        LOGGER.debug("Exhibition creation fail");
        return CommandResult.forward(Views.CREATE_EXHIBITION_VIEW);
    }
}

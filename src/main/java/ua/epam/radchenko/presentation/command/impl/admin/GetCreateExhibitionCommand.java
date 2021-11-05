package ua.epam.radchenko.presentation.command.impl.admin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCreateExhibitionCommand implements Command {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final ExhibitionService exhibitionService = context.getBean("exhibitionService", ExhibitionService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandResult.forward(Views.CREATE_EXHIBITION_VIEW);
    }
}

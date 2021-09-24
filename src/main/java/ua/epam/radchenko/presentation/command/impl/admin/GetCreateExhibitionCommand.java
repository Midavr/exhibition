package ua.epam.radchenko.presentation.command.impl.admin;

import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCreateExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService =
            ServiceFactory.getExhibitionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandResult.forward(Views.CREATE_EXHIBITION_VIEW);
    }
}

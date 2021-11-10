package ua.epam.radchenko.presentation.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.constants.Views;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandlerCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ErrorHandlerCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Throwable exception = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        LOGGER.error("Something go wrong!", exception);
        return CommandResult.forward(Views.ERROR_GLOBAL_VIEW);
    }
}

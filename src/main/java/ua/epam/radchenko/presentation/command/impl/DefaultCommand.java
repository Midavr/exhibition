package ua.epam.radchenko.presentation.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(DefaultCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.warn("Wrong request path");
        return CommandResult.redirect(PagesPaths.HOME_PATH);
    }
}
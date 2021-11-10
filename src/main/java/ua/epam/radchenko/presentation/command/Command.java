package ua.epam.radchenko.presentation.command;

import ua.epam.radchenko.presentation.FrontController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Specialize interface for command in front controller pattern.
 *
 * @see FrontController
 */
public interface Command {
    /**
     * Process request of user.
     *
     * @param request  HttpServletRequest to be processed
     * @param response HttpServletRequest
     * @return object of {@code CommandResult} class which contains path to appropriate jsp page
     * @see CommandResult
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}

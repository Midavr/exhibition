package ua.epam.radchenko.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandFactory;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.RedirectType;
import ua.epam.radchenko.presentation.util.RequestMethod;
import ua.epam.radchenko.presentation.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provide a centralized request handling mechanism to
 * handle all types of requests coming to the application.
 * <p>
 * Application main servlet responsible for:
 * 1. Obtaining commands from incoming request
 * 2. Executing commands
 * 3. Redirecting request further by parameters obtained from CommandResult object
 *
 * @see CommandResult
 */
public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

    private CommandFactory commandFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        commandFactory = CommandFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("GET: {}", request.getPathInfo());
        processRequest(request, response, RequestMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("POST: {}", request.getPathInfo());
        processRequest(request, response, RequestMethod.POST);
    }

    /**
     * Main dispatching method for all types of methods
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response,
                                RequestMethod method)
            throws ServletException, IOException {
        Command command = commandFactory.getCommand(
                getPath(request), method);
        CommandResult commandResult = command.execute(request, response);
        LOGGER.debug("Path of response: {}", commandResult.getPagePath());
        if (commandResult.getRedirectType() == RedirectType.REDIRECT) {
            Util.redirectTo(request, response, commandResult.getPagePath());
        } else {
            request.getRequestDispatcher(commandResult.getPagePath())
                    .forward(request, response);
        }
    }

    private String getPath(HttpServletRequest request) {
        return request.getPathInfo();
    }
}
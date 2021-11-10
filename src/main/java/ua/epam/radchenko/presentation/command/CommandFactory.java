package ua.epam.radchenko.presentation.command;

import ua.epam.radchenko.presentation.command.impl.DefaultCommand;
import ua.epam.radchenko.presentation.command.impl.ErrorHandlerCommand;
import ua.epam.radchenko.presentation.command.impl.HomeCommand;
import ua.epam.radchenko.presentation.command.impl.admin.GetAdminCatalogCommand;
import ua.epam.radchenko.presentation.command.impl.admin.GetCreateExhibitionCommand;
import ua.epam.radchenko.presentation.command.impl.admin.PostChangeStatusExhibitionCommand;
import ua.epam.radchenko.presentation.command.impl.admin.PostCreateExhibitionCommand;
import ua.epam.radchenko.presentation.command.impl.authorization.*;
import ua.epam.radchenko.presentation.command.impl.common.GetCatalogCommand;
import ua.epam.radchenko.presentation.command.impl.common.GetProfileCommand;
import ua.epam.radchenko.presentation.command.impl.user.*;
import ua.epam.radchenko.presentation.util.RequestMethod;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that is responsible for holding application endpoints and commands that
 * correspond to them
 */

public class CommandFactory {
    private final static String DELIMITER = ":";

    private final Command DEFAULT_COMMAND = new DefaultCommand();
    private final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        init();
    }

    private void init() {
        commands.put(buildKey(PagesPaths.HOME_PATH, RequestMethod.GET),
                new HomeCommand());
        commands.put(buildKey(PagesPaths.ERROR_PATH, RequestMethod.GET),
                new ErrorHandlerCommand());
        commands.put(buildKey(PagesPaths.CATALOG_PATH, RequestMethod.GET),
                new GetCatalogCommand());
        commands.put(buildKey(PagesPaths.SIGN_IN_PATH, RequestMethod.GET),
                new GetSignInCommand());
        commands.put(buildKey(PagesPaths.SIGN_IN_PATH, RequestMethod.POST),
                new PostSignInCommand());
        commands.put(buildKey(PagesPaths.SIGN_UP_PATH, RequestMethod.GET),
                new GetSignUpCommand());
        commands.put(buildKey(PagesPaths.SIGN_UP_PATH, RequestMethod.POST),
                new PostSignUpCommand());
        commands.put(buildKey(PagesPaths.SIGN_OUT_PATH, RequestMethod.GET),
                new SignOutCommand());
        commands.put(buildKey(PagesPaths.PROFILE_PATH, RequestMethod.GET),
                new GetProfileCommand());
        commands.put(buildKey(PagesPaths.CREATE_EXHIBITION_PATH, RequestMethod.GET),
                new GetCreateExhibitionCommand());
        commands.put(buildKey(PagesPaths.CREATE_EXHIBITION_PATH, RequestMethod.POST),
                new PostCreateExhibitionCommand());
        commands.put(buildKey(PagesPaths.CART_PATH, RequestMethod.GET),
                new GetShoppingCartCommand());
        commands.put(buildKey(PagesPaths.CART_ADD_ITEM_PATH, RequestMethod.POST),
                new PostCartAddItemCommand());
        commands.put(buildKey(PagesPaths.CART_REMOVE_ITEM_PATH, RequestMethod.POST),
                new PostCartRemoveItemCommand());
        commands.put(buildKey(PagesPaths.CART_REMOVE_ALL_ITEM_PATH, RequestMethod.POST),
                new PostCartRemoveAllItemCommand());
        commands.put(buildKey(PagesPaths.ORDERS_PATH, RequestMethod.GET),
                new GetOrdersCommand());
        commands.put(buildKey(PagesPaths.CART_TICKETS_PAYMENT_PATH, RequestMethod.POST),
                new PostOrderPaymentCommand());
        commands.put(buildKey(PagesPaths.ADMIN_CATALOG_PATH, RequestMethod.GET),
                new GetAdminCatalogCommand());
        commands.put(buildKey(PagesPaths.CHANGE_STATUS_EXHIBITION_PATH, RequestMethod.POST),
                new PostChangeStatusExhibitionCommand());

    }

    public Command getCommand(String path, RequestMethod method) {
        return commands.getOrDefault(buildKey(path, method), DEFAULT_COMMAND);
    }

    private String buildKey(String path, RequestMethod method) {
        return method.name() + DELIMITER + path;
    }

    public static class Singleton {
        private final static CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return Singleton.INSTANCE;
    }
}

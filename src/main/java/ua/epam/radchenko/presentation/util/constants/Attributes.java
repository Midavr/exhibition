package ua.epam.radchenko.presentation.util.constants;

import ua.epam.radchenko.util.ResourceManager;

/**
 * Constants which relates to view part
 */
public class Attributes {
    public static final String USER = ResourceManager.ATTRIBUTE.getProperty("user");
    public static final String SHOPPING_CART = ResourceManager.ATTRIBUTE.getProperty("shopping.cart");
    public static final String CATALOG = ResourceManager.ATTRIBUTE.getProperty("catalog");
    public static final String ACTIVE_ORDERS = ResourceManager.ATTRIBUTE.getProperty("orders.active");
    public static final String EXPIRED_ORDERS = ResourceManager.ATTRIBUTE.getProperty("orders.expired");

    public static final String PAGINATION_PAGE = ResourceManager.ATTRIBUTE.getProperty("pagination.page");
    public static final String PAGINATION_NUMBER_OF_PAGES = ResourceManager.ATTRIBUTE.getProperty("pagination.number.pages");
    public static final String USER_DTO = ResourceManager.ATTRIBUTE.getProperty("user.dto");
    public static final String EXHIBITION_DTO = ResourceManager.ATTRIBUTE.getProperty("exhibition.dto");

    public static final String PAGINATION_ACTIVE_ORDERS_PAGE = ResourceManager.ATTRIBUTE.getProperty("pagination.active.orders.page");
    public static final String PAGINATION_EXPIRED_ORDERS_PAGE = ResourceManager.ATTRIBUTE.getProperty("pagination.expired.orders.page");
    public static final String PAGINATION_ACTIVE_ORDERS_NUMBER_OF_PAGES = ResourceManager.ATTRIBUTE.getProperty("pagination.number.active.orders.page");
    public static final String PAGINATION_EXPIRED_ORDERS_NUMBER_OF_PAGES = ResourceManager.ATTRIBUTE.getProperty("pagination.number.expired.orders.page");

    public static final String ERRORS = ResourceManager.ATTRIBUTE.getProperty("errors");
    public static final String ERROR_LOGIN = ResourceManager.ATTRIBUTE.getProperty("error.login");
    public static final String ERROR_PASSWORD = ResourceManager.ATTRIBUTE.getProperty("error.password");
    public static final String ERROR_AUTHENTICATION = ResourceManager.ATTRIBUTE.getProperty("error.authentication");
    public static final String ERROR_FIRST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.firstname");
    public static final String ERROR_LAST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.lastname");
    public static final String ERROR_REGISTRATION = ResourceManager.ATTRIBUTE.getProperty("error.registration");
    public static final String ERROR_IS_ALREADY_IN_CART = ResourceManager.ATTRIBUTE.getProperty("error.is.already.in.cart");

    public static final String ERROR_EXHIBITION_NAME = ResourceManager.ATTRIBUTE.getProperty("error.exhibition.name");
    public static final String ERROR_EXHIBITION_DESCRIPTION = ResourceManager.ATTRIBUTE.getProperty("error.exhibition.description");
    public static final String ERROR_EXHIBITION_PRICE = ResourceManager.ATTRIBUTE.getProperty("error.exhibition.price");
    public static final String ERROR_EXHIBITION_INVALID = ResourceManager.ATTRIBUTE.getProperty("error.exhibition.invalid");

    private Attributes() {}
}

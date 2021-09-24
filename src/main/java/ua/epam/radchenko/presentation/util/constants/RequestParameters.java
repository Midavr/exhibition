package ua.epam.radchenko.presentation.util.constants;

import ua.epam.radchenko.util.ResourceManager;

/**
 * Constants which relates to parameters in HttpServletRequest
 */
public class RequestParameters {
    public static final String PAGINATION_PAGE = ResourceManager.PARAMETER.getProperty("pagination.page");
    public static final String PAGINATION_PILL = ResourceManager.PARAMETER.getProperty("pagination.pill");
    public static final String PAGINATION_WAY = ResourceManager.PARAMETER.getProperty("pagination.way");
    public static final String PAGINATION_TYPE = ResourceManager.PARAMETER.getProperty("pagination.type");
    public static final String PAGINATION_SORT_DATE = ResourceManager.PARAMETER.getProperty("pagination.sort.date");

    public static final String ERROR_ATTRIBUTE = ResourceManager.PARAMETER.getProperty("error.attribute");

    public static final String EXHIBITION_ID = ResourceManager.PARAMETER.getProperty("exhibition.id");
    public static final String EXHIBITION_NAME = ResourceManager.PARAMETER.getProperty("exhibition.name");
    public static final String EXHIBITION_DESCRIPTION = ResourceManager.PARAMETER.getProperty("exhibition.description");
    public static final String EXHIBITION_PRICE = ResourceManager.PARAMETER.getProperty("exhibition.price");
    public static final String EXHIBITION_DATE_START = ResourceManager.PARAMETER.getProperty("exhibition.date_start");
    public static final String EXHIBITION_DATE_END = ResourceManager.PARAMETER.getProperty("exhibition.date_end");
    public static final String EXHIBITION_THEME = ResourceManager.PARAMETER.getProperty("exhibition.theme");
    public static final String EXHIBITION_STATUS = ResourceManager.PARAMETER.getProperty("exhibition.status");
    public static final String EXHIBITION_HALL = ResourceManager.PARAMETER.getProperty("exhibition.hall");

    public static final String PAGINATION_ACTIVE_ORDERS_PAGE = ResourceManager.PARAMETER.getProperty("pagination.active.orders.page");
    public static final String PAGINATION_EXPIRED_ORDERS_PAGE = ResourceManager.PARAMETER.getProperty("pagination.expired.orders.page");


    public static final String USER_FIRST_NAME = ResourceManager.PARAMETER.getProperty("user.first.name");
    public static final String USER_LAST_NAME = ResourceManager.PARAMETER.getProperty("user.last.name");
    public static final String USER_DATE_OF_BIRTH = ResourceManager.PARAMETER.getProperty("user.date.of.birth");
    public static final String USER_GENDER = ResourceManager.PARAMETER.getProperty("user.gender");
    public static final String USER_LOGIN = ResourceManager.PARAMETER.getProperty("user.login");
    public static final String USER_PASSWORD = ResourceManager.PARAMETER.getProperty("user.password");

    public static final String CART_ITEM_ID = ResourceManager.PARAMETER.getProperty("cart.item.id");

    private RequestParameters() {}
}

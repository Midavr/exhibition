package ua.epam.radchenko.presentation.util.constants;


import ua.epam.radchenko.util.ResourceManager;

/**
 * Relative path of pages
 */
public class PagesPaths {
    public static final String SITE_PREFIX = ResourceManager.PATH.getProperty("site.prefix");

    public static final String SIGN_IN_PATH = ResourceManager.PATH.getProperty("path.signin");
    public static final String SIGN_UP_PATH = ResourceManager.PATH.getProperty("path.signup");
    public static final String SIGN_OUT_PATH = ResourceManager.PATH.getProperty("path.signout");

    public static final String HOME_PATH = ResourceManager.PATH.getProperty("path.home");
    public static final String ERROR_PATH = ResourceManager.PATH.getProperty("path.error");
    public static final String CATALOG_PATH = ResourceManager.PATH.getProperty("path.catalog");
    public static final String PROFILE_PATH = ResourceManager.PATH.getProperty("path.profile");

    public static final String ADMIN_CATALOG_PATH = ResourceManager.PATH.getProperty("path.admin.catalog");
    public static final String CREATE_EXHIBITION_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.exhibition.create");
    public static final String CHANGE_STATUS_EXHIBITION_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.change.status");

    public static final String CART_PATH = ResourceManager.PATH.getProperty("path.cart");
    public static final String CART_ADD_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.add.item");
    public static final String CART_REMOVE_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.remove.item");
    public static final String CART_REMOVE_ALL_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.remove.all.item");
    public static final String CART_TICKETS_PAYMENT_PATH = ResourceManager.PATH.getProperty("path.cart.tickets.payment");
    public static final String ORDERS_PATH = ResourceManager.PATH.getProperty("path.user.orders");


    private PagesPaths() {}
}

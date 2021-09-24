package ua.epam.radchenko.presentation.util.constants;

import ua.epam.radchenko.util.ResourceManager;

/**
 * Path to jsp
 */
public class Views {
    public static final String FOLDER = ResourceManager.VIEW.getProperty("view.folder");
    public static final String HOME_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.home");
    public static final String CATALOG_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.catalog");
    public static final String SIGN_IN_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.signin");
    public static final String SIGN_UP_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.signup");
    public static final String PROFILE_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.profile");
    public static final String CART_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.cart");
    public static final String ORDERS_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.orders");

    public static final String CREATE_EXHIBITION_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.create.exhibition");
    public static final String ADMIN_CATALOG_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.admin.catalog");


    public static final String ERROR_GLOBAL_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.error.global");

    private Views() {}
}

package ua.epam.radchenko.service;

public class ServiceFactory {
    public static ExhibitionService getExhibitionService() {
        return ExhibitionService.getInstance();
    }

    public static UserService getUserService() {
        return UserService.getInstance();
    }

    public static ShoppingCartService getShoppingCartService() {
        return ShoppingCartService.getInstance();
    }

    public static OrderService getOrderService() {
        return OrderService.getInstance();
    }
}

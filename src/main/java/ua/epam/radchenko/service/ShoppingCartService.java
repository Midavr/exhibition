package ua.epam.radchenko.service;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.service.entity.ShoppingCart;

public interface ShoppingCartService {

    boolean addItemToCart(ShoppingCart shoppingCart, User user, Exhibition exhibition);

    void removeItemFromCart(ShoppingCart shoppingCart, Long cartItemId);

    void removeAllItemFromCart(ShoppingCart shoppingCart);

    void updateShoppingCartItemsFromDatabase(ShoppingCart shoppingCart);

}

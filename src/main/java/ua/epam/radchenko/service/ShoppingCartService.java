package ua.epam.radchenko.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.service.entity.ShoppingCart;
import ua.epam.radchenko.service.exeption.ServiceException;

import java.util.Optional;

/**
 * Service responsible for processing some actions
 * that has to do with shopping cart
 * @see ShoppingCart
 */
@Service
public class ShoppingCartService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ShoppingCartService.class);

    private final ExhibitionService exhibitionService;

    public ShoppingCartService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public boolean addItemToCart(ShoppingCart shoppingCart,
                                 User user,
                                 Exhibition exhibition) {
        LOGGER.debug("Attempt to add item to cart");
        Order order = Order.newBuilder()
                .setUserId(user)
                .setExhibitionId(exhibition)
                .build();
        return shoppingCart.addItem(order);
    }

    public void removeItemFromCart(ShoppingCart shoppingCart, Long cartItemId) {
        LOGGER.debug("Attempt to remove item from cart");
        shoppingCart.removeItem(cartItemId);
    }

    public void removeAllItemFromCart(ShoppingCart shoppingCart) {
        LOGGER.debug("Attempt to remove all item from cart");
        shoppingCart.removeAll();
    }

    public void updateShoppingCartItemsFromDatabase(ShoppingCart shoppingCart) {
        for (Order order : shoppingCart.getItems()) {
            Optional<Exhibition> exhibitionOpt = exhibitionService.findExhibitionById(order.getExhibitionId().getExhibitionId());
            if (exhibitionOpt.isPresent()) {
                order.setExhibitionId(exhibitionOpt.get());
                shoppingCart.updateItem(order);
            } else {
                LOGGER.error("Order cannot refer to a non-existent exhibition");
                throw new ServiceException("Order cannot refer to a non-existent exhibition");
            }
        }
    }
}

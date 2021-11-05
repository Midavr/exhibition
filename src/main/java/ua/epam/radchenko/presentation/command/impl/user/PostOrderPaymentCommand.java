package ua.epam.radchenko.presentation.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.Util;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;
import ua.epam.radchenko.service.OrderService;
import ua.epam.radchenko.service.ShoppingCartService;
import ua.epam.radchenko.service.entity.ShoppingCart;
import ua.epam.radchenko.service.exeption.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class PostOrderPaymentCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PostOrderPaymentCommand.class);
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final OrderService orderService = context.getBean("orderService", OrderService.class);
    private final ShoppingCartService shoppingCartService = context.getBean("shoppingCartService", ShoppingCartService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to process new orders");
        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart);

        User user = Util.getAuthorizedUser(request.getSession());
        List<Order> orders = shoppingCart.getItems();
        BigDecimal totalPrice = shoppingCart.getTotalPrice();

        try {
            orderService.processOrders(user, orders, totalPrice);
        } catch (ServiceException exception) {
            LOGGER.error(exception.getMessage());
            return CommandResult.redirect(PagesPaths.CART_PATH);
        }

        shoppingCartService.removeAllItemFromCart(shoppingCart);
        LOGGER.debug("New orders processed successfully");
        return CommandResult.redirect(PagesPaths.CART_PATH);
    }
}

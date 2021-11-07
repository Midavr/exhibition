package ua.epam.radchenko.presentation.command.impl.user;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.Util;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.ShoppingCartService;
import ua.epam.radchenko.service.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetShoppingCartCommand implements Command {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ShoppingCartService shoppingCartService = context.getBean("shoppingCartServiceImpl", ShoppingCartService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart);
        return CommandResult.forward(Views.CART_VIEW);
    }
}

package ua.epam.radchenko.presentation.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.Util;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.service.ServiceFactory;
import ua.epam.radchenko.service.ShoppingCartService;
import ua.epam.radchenko.service.entity.ShoppingCart;
import ua.epam.radchenko.util.type.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PostCartAddItemCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PostCartAddItemCommand.class);
    private final ExhibitionService exhibitionService = ServiceFactory.getExhibitionService();
    private final ShoppingCartService shoppingCartService = ServiceFactory.getShoppingCartService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to add item to shopping cart");
        String referer = Util.getReferer(request);
        referer = Util.removeParameterFromURI(referer, RequestParameters.ERROR_ATTRIBUTE);

        User user = Util.getAuthorizedUser(request.getSession());
        int exhibitionId = Integer.parseInt(
                request.getParameter(RequestParameters.EXHIBITION_ID));
        Optional<Exhibition> exhibitionOpt =
                exhibitionService.findExhibitionById(exhibitionId);

        if (!exhibitionOpt.isPresent() ||
                exhibitionOpt.get().getExhibitionStatus() == Status.SUSPENDED) {
            referer = Util.addParameterToURI(referer,
                    RequestParameters.ERROR_ATTRIBUTE,
                    Attributes.ERROR_EXHIBITION_INVALID);
            LOGGER.debug("Invalid exhibition with id {} for ticket", exhibitionId);
            return CommandResult.redirect(referer);
        }

        Exhibition exhibition = exhibitionOpt.get();

        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        boolean isAddedToCart = shoppingCartService.addItemToCart(shoppingCart, user,
                exhibition);
        if (!isAddedToCart) {
            referer = Util.addParameterToURI(referer,
                    RequestParameters.ERROR_ATTRIBUTE,
                    Attributes.ERROR_IS_ALREADY_IN_CART);
            LOGGER.debug("Item already exists in cart");
        } else {
            LOGGER.debug("Item successfully added to cart");
        }

        return CommandResult.redirect(referer);
    }
}

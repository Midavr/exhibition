package ua.epam.radchenko.presentation.command.impl.user;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.PaginationManager;
import ua.epam.radchenko.presentation.util.Util;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class GetOrdersCommand implements Command {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final OrderService orderService = context.getBean("orderService", OrderService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        PaginationManager activeOrdersPagination = new PaginationManager(5,
                RequestParameters.PAGINATION_ACTIVE_ORDERS_PAGE,
                Attributes.PAGINATION_ACTIVE_ORDERS_PAGE,
                Attributes.PAGINATION_ACTIVE_ORDERS_NUMBER_OF_PAGES);
        PaginationManager expiredOrdersPagination = new PaginationManager(5,
                RequestParameters.PAGINATION_EXPIRED_ORDERS_PAGE,
                Attributes.PAGINATION_EXPIRED_ORDERS_PAGE,
                Attributes.PAGINATION_EXPIRED_ORDERS_NUMBER_OF_PAGES);
        String pill = request.getParameter(RequestParameters.PAGINATION_PILL);
        if (Objects.nonNull(pill)) {
            request.setAttribute(pill, true);
        }

        User user = Util.getAuthorizedUser(request.getSession());
        long activeRowsCount = orderService.getOrdersCountByUserAndStatus(user, false);
        long expiredRowsCount = orderService.getOrdersCountByUserAndStatus(user, true);
        long activeSkip = activeOrdersPagination.manage(request, activeRowsCount);
        long expiredSkip = expiredOrdersPagination.manage(request, expiredRowsCount);

        List<Order> activeOrders =
                orderService.findAllOrdersByUserAndStatus(user,
                        false,
                        activeSkip,
                        activeOrdersPagination.getRecordsPerPage());
        List<Order> expiredOrders =
                orderService.findAllOrdersByUserAndStatus(user,
                        true,
                        expiredSkip,
                        expiredOrdersPagination.getRecordsPerPage());

        request.setAttribute(Attributes.ACTIVE_ORDERS, activeOrders);
        request.setAttribute(Attributes.EXPIRED_ORDERS, expiredOrders);
        return CommandResult.forward(Views.ORDERS_VIEW);
    }
}

package ua.epam.radchenko.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.dao.OrderDao;
import ua.epam.radchenko.persistence.dao.factory.DaoFactory;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.persistence.transaction.Transaction;
import ua.epam.radchenko.service.exeption.ServiceException;
import ua.epam.radchenko.util.type.Status;

import java.math.BigDecimal;
import java.util.List;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing order-related operations
 */
@Service
public class OrderService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrderService.class);
    private OrderDao orderDao =
            DaoFactory.getInstance().getOrderDao();
    private final ExhibitionService exhibitionService;

    public OrderService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public List<Order> findAllOrdersByUserAndStatus(User user,
                                                                  boolean isExpired,
                                                                  long skip,
                                                                  long limit) {
        LOGGER.debug("Attempt to find all orders by user and status");
        return orderDao.findByUserAndStatus(user, isExpired, skip, limit);
    }

    public long getOrdersCountByUserAndStatus(User user, boolean isExpired) {
        LOGGER.debug("Attempt to get active orders count by user");
        return orderDao.getCountByUserAndStatus(user, isExpired);
    }

    public void processOrders(User user,
                                     List<Order> orders,
                                     BigDecimal totalPrice) {
        LOGGER.debug("Attempt to process orders");
        if (orders.size() != 0) {
            Transaction.doTransaction(() -> {
                for (Order order : orders) {
                    Exhibition exhibition = exhibitionService.findExhibitionById(order.getExhibitionId().getExhibitionId()).orElseThrow(() -> new ServiceException(
                            "Order cannot refer to a non-existent exhibition"));
                    if (exhibition.getExhibitionStatus() == Status.SUSPENDED) {
                        throw new ServiceException("Can't subscribe to exhibition with SUSPEND status");
                    }

                    order.setExhibitionId(exhibition);
                    orderDao.insert(order);
                }
            });
        }
    }

    public List<Integer> countExhibitionsOrders(List<Exhibition> exhibitions) {
        LOGGER.debug("Attempt to count all exhibition orders");
        return orderDao.countByOrders(exhibitions);
    }

}

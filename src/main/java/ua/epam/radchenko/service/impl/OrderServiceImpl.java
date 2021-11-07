package ua.epam.radchenko.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.epam.radchenko.persistence.dao.OrderDao;
import ua.epam.radchenko.persistence.dao.factory.DaoFactory;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.persistence.transaction.Transaction;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.service.OrderService;
import ua.epam.radchenko.service.exeption.ServiceException;
import ua.epam.radchenko.util.timed.Timed;
import ua.epam.radchenko.util.type.Status;

import java.math.BigDecimal;
import java.util.List;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing order-related operations
 */

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao =
            DaoFactory.getInstance().getOrderDao();
    private final ExhibitionService exhibitionService;

    public OrderServiceImpl(ExhibitionService exhibitionServiceImpl) {
        this.exhibitionService = exhibitionServiceImpl;
    }

    @Override
    public List<Order> findAllOrdersByUserAndStatus(User user,
                                                                  boolean isExpired,
                                                                  long skip,
                                                                  long limit) {
        LOGGER.debug("Attempt to find all orders by user and status");
        return orderDao.findByUserAndStatus(user, isExpired, skip, limit);
    }

    @Override
    public long getOrdersCountByUserAndStatus(User user, boolean isExpired) {
        LOGGER.debug("Attempt to get active orders count by user");
        return orderDao.getCountByUserAndStatus(user, isExpired);
    }

    @Override
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

    @Override
    public List<Integer> countExhibitionsOrders(List<Exhibition> exhibitions) {
        LOGGER.debug("Attempt to count all exhibition orders");
        return orderDao.countByOrders(exhibitions);
    }

}

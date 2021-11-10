package ua.epam.radchenko.service;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    List<Order> findAllOrdersByUserAndStatus(User user, boolean isExpired, long skip, long limit);

    long getOrdersCountByUserAndStatus(User user, boolean isExpired);

    void processOrders(User user, List<Order> orders, BigDecimal totalPrice);

    List<Integer> countExhibitionsOrders(List<Exhibition> exhibitions);
}

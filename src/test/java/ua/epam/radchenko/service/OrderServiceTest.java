package ua.epam.radchenko.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.epam.radchenko.persistence.dao.OrderDao;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService = OrderService.getInstance();
    @Mock
    private OrderDao orderDao;

    @Test
    void findAllOrdersByUserAndStatusTest() {
        long skip = 0;
        long limit = 3;
        final boolean isExpired = false;
        User user = User.newBuilder()
                .setUserId(1)
                .build();
        List<Order> expected = new ArrayList<Order>() {{
            add(Order.newBuilder().setOrderId(1).build());
            add(Order.newBuilder().setOrderId(2).build());
            add(Order.newBuilder().setOrderId(3).build());
        }};
        when(orderDao.findByUserAndStatus(user, isExpired, skip, limit))
                .thenReturn(expected);

        List<Order> actual =
                orderService.findAllOrdersByUserAndStatus(user, isExpired, skip, limit);

        assertEquals(3, actual.size());
        verify(orderDao, times(1))
                .findByUserAndStatus(user, isExpired, skip, limit);
    }

    @Test
    void getOrdersCountByUserAndStatusTest() {
        final boolean isExpired = false;
        User user = User.newBuilder()
                .setUserId(1)
                .build();
        long expected = 10;
        when(orderDao.getCountByUserAndStatus(user, isExpired))
                .thenReturn(expected);

        long actual =
                orderService.getOrdersCountByUserAndStatus(user, isExpired);

        assertEquals(expected, actual);
        verify(orderDao, times(1))
                .getCountByUserAndStatus(user, isExpired);
    }

}
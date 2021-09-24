package ua.epam.radchenko.service.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private ShoppingCart shoppingCart;

    @BeforeEach
    private void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    void addItemTest() {
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionId(1)
                .build();
        Order order = Order.newBuilder()
                .setExhibitionId(exhibition)
                .build();

        assertEquals(0, shoppingCart.size());
        assertTrue(shoppingCart.addItem(order));
        assertEquals(1, shoppingCart.size());
        assertFalse(shoppingCart.addItem(order));
        assertEquals(1, shoppingCart.size());
    }

    @Test
    void removeItemTest() {
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionId(1)
                .build();
        Order order = Order.newBuilder()
                .setExhibitionId(exhibition)
                .build();

        assertTrue(shoppingCart.addItem(order));
        assertEquals(1, shoppingCart.size());
        shoppingCart.removeItem(1);
        assertEquals(0, shoppingCart.size());
    }

    @Test
    void removeItemInvalidIdTest() {
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionId(1)
                .build();
        Order order = Order.newBuilder()
                .setExhibitionId(exhibition)
                .build();

        assertTrue(shoppingCart.addItem(order));
        assertEquals(1, shoppingCart.size());

        shoppingCart.removeItem(999);

        assertEquals(1, shoppingCart.size());
    }

    @Test
    void getTotalPriceTest() {
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionId(1)
                .setPrice(new BigDecimal("1"))
                .build();
        Exhibition exhibition2 = Exhibition.newBuilder()
                .setExhibitionId(2)
                .setPrice(new BigDecimal("2"))
                .build();
        Exhibition exhibition3 = Exhibition.newBuilder()
                .setExhibitionId(3)
                .setPrice(new BigDecimal("3.3"))
                .build();
        Exhibition exhibition4 = Exhibition.newBuilder()
                .setExhibitionId(4)
                .setPrice(new BigDecimal("4"))
                .build();
        List<Order> orders = new ArrayList<Order>() {{
            add(Order.newBuilder()
                    .setExhibitionId(exhibition)
                    .build());
            add(Order.newBuilder()
                    .setExhibitionId(exhibition2)
                    .build());
            add(Order.newBuilder()
                    .setExhibitionId(exhibition3)
                    .build());
            add(Order.newBuilder()
                    .setExhibitionId(exhibition4)
                    .build());
        }};

        BigDecimal expectedTotalPrice = new BigDecimal("1");
        shoppingCart.addItem(orders.get(0));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("3");
        shoppingCart.addItem(orders.get(1));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("6.3");
        shoppingCart.addItem(orders.get(2));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("10.3");
        shoppingCart.addItem(orders.get(3));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));
    }

    @Test
    void getItemsTest() {
        Exhibition exhibition1 = Exhibition.newBuilder()
                .setExhibitionId(1)
                .build();
        Exhibition exhibition2 = Exhibition.newBuilder()
                .setExhibitionId(2)
                .build();
        List<Order> expected = new ArrayList<Order>() {{
            add(Order.newBuilder()
                    .setExhibitionId(exhibition1)
                    .build());
            add(Order.newBuilder()
                    .setExhibitionId(exhibition2)
                    .build());
        }};

        shoppingCart.addItem(expected.get(0));
        shoppingCart.addItem(expected.get(1));

        List<Order> actual = shoppingCart.getItems();

        assertNotSame(expected, actual);
        assertEquals(expected, actual);
    }

}
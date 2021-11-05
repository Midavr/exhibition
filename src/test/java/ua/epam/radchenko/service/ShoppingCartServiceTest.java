package ua.epam.radchenko.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.service.entity.ShoppingCart;
import ua.epam.radchenko.service.exeption.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {
    @InjectMocks
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private ShoppingCartService shoppingCartService = context.getBean("shoppingCartService", ShoppingCartService.class);
    @Mock
    private ExhibitionService exhibitionService;

    @Test
    void addItemToCartTest() {
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        User user = User.newBuilder()
                .setUserId(1)
                .build();
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionId(1)
                .build();

        shoppingCartService.addItemToCart(shoppingCart, user, exhibition);

        verify(shoppingCart, times(1)).addItem(any(Order.class));
    }

    @Test
    void removeItemFromCartTest() {
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        long cartItemId = 1L;

        shoppingCartService.removeItemFromCart(shoppingCart, cartItemId);

        verify(shoppingCart, times(1)).removeItem(cartItemId);
    }

    @Test
    void removeAllItemFromCartTest() {
        ShoppingCart shoppingCart = mock(ShoppingCart.class);

        shoppingCartService.removeAllItemFromCart(shoppingCart);

        verify(shoppingCart, times(1)).removeAll();
    }

    @Test
    void updateShoppingCartItemsFromDatabaseTest() {
        int exhibitionId = 1;
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        User user = User.newBuilder()
                .setUserId(1)
                .build();
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionId(exhibitionId)
                .build();
        Order item = Order.newBuilder()
                .setUserId(user)
                .setExhibitionId(exhibition)
                .build();
        List<Order> items = new ArrayList<Order>() {{
            add(item);
        }};
        when(shoppingCart.getItems()).thenReturn(items);
        when(exhibitionService.findExhibitionById(exhibitionId))
                .thenReturn(Optional.of(exhibition));

        shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart);

        verify(shoppingCart, times(1)).updateItem(item);
    }

    @Test
    void updateShoppingCartItemsFromDatabaseWithExceptionTest() {
        int exhibitionId = 1;
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        User user = User.newBuilder()
                .setUserId(1)
                .build();
        Exhibition exhibition = Exhibition.newBuilder()
                .setExhibitionId(exhibitionId)
                .build();
        Order item = Order.newBuilder()
                .setUserId(user)
                .setExhibitionId(exhibition)
                .build();
        List<Order> items = new ArrayList<Order>() {{
            add(item);
        }};
        when(shoppingCart.getItems()).thenReturn(items);
        when(exhibitionService.findExhibitionById(exhibitionId))
                .thenReturn(Optional.empty());

        ServiceException e = assertThrows(ServiceException.class, () ->
                shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart));
        assertEquals("Order cannot refer to a non-existent exhibition", e.getMessage());
        verify(shoppingCart, never()).updateItem(item);
    }
}
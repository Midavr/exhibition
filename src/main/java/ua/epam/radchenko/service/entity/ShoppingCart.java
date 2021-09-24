package ua.epam.radchenko.service.entity;

import ua.epam.radchenko.persistence.entity.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents virtual shopping cart for storing items
 */
public class ShoppingCart {
    private final List<Order> items = new ArrayList<>();

    public boolean addItem(Order order) {
        if (isInCart(order)) {
            return false;
        }
        return items.add(order);
    }

    public void removeItem(long cartItemId) {
        items.removeIf(item -> getCartItemId(item) == cartItemId);
    }

    public void removeAll() {
        items.clear();
    }

    public List<Order> getItems() {
        return new ArrayList<>(items);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalValue = new BigDecimal(0);
        for (Order order : items) {
            BigDecimal price = order.getExhibitionId().getPrice();
            totalValue = totalValue.add(price);
        }
        return totalValue.setScale(2, RoundingMode.HALF_EVEN);
    }

    public int size() {
        return items.size();
    }

    public void updateItem(Order updatedOrder) {
        if (isInCart(updatedOrder)) {
            removeItem(getCartItemId(updatedOrder));
            items.add(updatedOrder);
        }
    }

    private boolean isInCart(Order orderToCheck) {
        long cartItemId = getCartItemId(orderToCheck);
        return items.stream().anyMatch(item -> getCartItemId(item) == cartItemId);
    }

    private long getCartItemId(Order order) {
        return order.getExhibitionId().getExhibitionId();
    }

}

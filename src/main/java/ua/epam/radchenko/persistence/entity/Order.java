package ua.epam.radchenko.persistence.entity;


import java.io.Serializable;
import java.util.Objects;

/**
 * Class that represents user orders of some exhibition
 */
public class Order implements Serializable {
    private Integer orderId;
    private Exhibition exhibitionId;
    private User userId;

    public static class Builder {
        private final Order order;

        public Builder() {
            this.order = new Order();
        }

        public Builder setOrderId(Integer orderId) {
            order.setOrderId(orderId);
            return this;
        }

        public Builder setExhibitionId(Exhibition exhibitionId) {
            order.setExhibitionId(exhibitionId);
            return this;
        }

        public Builder setUserId(User userId) {
            order.setUserId(userId);
            return this;
        }

        public Order build() {
            return order;
        }
    }

    public static Order.Builder newBuilder() {return new Builder();}

    public Order(){}

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Exhibition getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Exhibition exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", exhibitionId=" + exhibitionId +
                ", userId=" + userId +
                '}';
    }
}

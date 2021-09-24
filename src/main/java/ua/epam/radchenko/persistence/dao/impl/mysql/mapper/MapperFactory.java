package ua.epam.radchenko.persistence.dao.impl.mysql.mapper;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;

public class MapperFactory {
    private static final EntityMapper<Exhibition> EXHIBITION_MAPPER = new ExhibitionMapper();
    private static final EntityMapper<User> USER_MAPPER = new UserMapper();
    private static final EntityMapper<Order> ORDER_MAPPER = new OrderMapper();

    public static EntityMapper<Exhibition> getExhibitionMapper() {
        return EXHIBITION_MAPPER;
    }

    public static EntityMapper<User> getUserMapper() {
        return USER_MAPPER;
    }

    public static EntityMapper<Order> getOrderMapper() {
        return ORDER_MAPPER;
    }
}

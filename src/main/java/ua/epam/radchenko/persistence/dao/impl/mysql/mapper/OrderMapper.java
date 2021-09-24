package ua.epam.radchenko.persistence.dao.impl.mysql.mapper;

import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements EntityMapper<Order> {
    private static final String ORDER_ID_FIELD = "order_id";

    private final EntityMapper<User> userMapper;
    private final EntityMapper<Exhibition> exhibitionMapper;

    public OrderMapper() {
        this(new UserMapper(), new ExhibitionMapper());
    }

    public OrderMapper(EntityMapper<User> userMapper,
                      EntityMapper<Exhibition> exhibitionMapper) {
        this.userMapper = userMapper;
        this.exhibitionMapper = exhibitionMapper;
    }

    @Override
    public Order mapToObject(ResultSet resultSet, String tablePrefix)
            throws SQLException {
        User userType = userMapper.mapToObject(resultSet);
        Exhibition exhibitionType = exhibitionMapper.mapToObject(resultSet);

        return Order.newBuilder()
                .setOrderId(resultSet.getInt(tablePrefix + ORDER_ID_FIELD))
                .setExhibitionId(exhibitionType)
                .setUserId(userType)
                .build();
    }

}

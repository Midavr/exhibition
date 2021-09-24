package ua.epam.radchenko.persistence.dao;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order, Integer> {

    /**
     * Retrieves count of objects from database.
     *
     * @param user user of system
     * @param isExpired this expired or active order
     * @return count of active orders associated with certain user.
     */
    long getCountByUserAndStatus(User user, boolean isExpired);

    /**
     * Retrieves all active orders associated with certain user.
     *
     * @param user      user of system
     * @param isExpired this expired or active order
     * @param skip      skip
     * @param limit     limit
     * @return list of retrieved orders
     */
    List<Order> findByUserAndStatus(User user, boolean isExpired, long skip, long limit);


    List<Integer> countByOrders(List<Exhibition> exhibitions);
}

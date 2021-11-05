package ua.epam.radchenko.persistence.dao.impl.mysql;

import ua.epam.radchenko.persistence.dao.OrderDao;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.EntityMapper;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.MapperFactory;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.persistence.exepion.DaoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderMySqlDao implements OrderDao {

    private final UtilMySqlDao<Order> utilMySqlDao;

    public OrderMySqlDao() {
        this(MapperFactory.getOrderMapper());
    }

    public OrderMySqlDao(EntityMapper<Order> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public OrderMySqlDao(UtilMySqlDao<Order> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Order> findOne(Integer id) {
        return utilMySqlDao.findOne("SELECT * FROM orders JOIN exhibitions" +
                " ON orders.exhibition_id = exhibitions.exhibition_id" +
                " JOIN users ON orders.user_id = users.user_id WHERE order_id = ?", id);
    }

    @Override
    public List<Order> findAll() {
        return utilMySqlDao.findAll("SELECT * FROM orders JOIN exhibitions" +
                " ON orders.exhibition_id = exhibitions.exhibition_id" +
                " JOIN users ON orders.user_id = users.user_id");
    }

    @Override
    public List<Order> findAll(long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return utilMySqlDao.findAll("SELECT * FROM orders JOIN exhibitions" +
                " ON orders.exhibition_id = exhibitions.exhibition_id" +
                " JOIN users ON orders.user_id = users.user_id LIMIT ?,?", skip, limit);
    }

    @Override
    public List<Order> viewAllSorted(long skip, long limit, String sorting, String sortingType, LocalDate sortDate) {
        return null;
    }

    @Override
    public Order insert(Order obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to insert nullable Order");
        }
        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                "INSERT INTO orders (exhibition_id, user_id) VALUES(?, ?)",
                Integer.class,
                obj.getExhibitionId().getExhibitionId(),
                obj.getUserId().getUserId());
        obj.setOrderId(id);

        return obj;
    }

    @Override
    public void update(Order obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to update nullable Order");
        }

        utilMySqlDao.executeUpdate(
                "UPDATE orders SET exhibition_id = ?, user_id = ? WHERE order_id = ?",
                obj.getExhibitionId().getExhibitionId(),
                obj.getUserId().getUserId(),
                obj.getOrderId());
    }

    @Override
    public void delete(Integer id) {
        utilMySqlDao.executeUpdate(
                "DELETE FROM orders WHERE order_id = ?",
                id);
    }

    @Override
    public long getCount() {
        return utilMySqlDao.getRowsCount("SELECT COUNT(order_id) FROM orders JOIN exhibitions ON orders.exhibition_id = exhibitions.exhibition_id");
    }

    @Override
    public long countDateFiltered(LocalDate sortDate) {
        return 0;
    }

    /**
     * Retrieves count of objects from database.
     *
     * @param user      user of system
     * @param isExpired this expired or active order
     * @return count of active orders associated with certain user.
     */
    @Override
    public long getCountByUserAndStatus(User user, boolean isExpired) {
        return isExpired
                ? utilMySqlDao.getRowsCount("SELECT COUNT(order_id) FROM orders JOIN exhibitions ON orders.exhibition_id = exhibitions.exhibition_id WHERE orders.user_id = ? AND exhibitions.date_end < curdate()", user.getUserId())
                : utilMySqlDao.getRowsCount("SELECT COUNT(order_id) FROM orders JOIN exhibitions ON orders.exhibition_id = exhibitions.exhibition_id WHERE orders.user_id = ? AND exhibitions.date_end >= curdate()", user.getUserId());
    }

    /**
     * Retrieves all active orders associated with certain user.
     *
     * @param user      user of system
     * @param isExpired this expired or active order
     * @param skip      skip
     * @param limit     limit
     * @return list of retrieved orders
     */
    @Override
    public List<Order> findByUserAndStatus(User user, boolean isExpired, long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return isExpired
                ? utilMySqlDao.findAll(
                "SELECT * FROM orders JOIN exhibitions ON orders.exhibition_id = exhibitions.exhibition_id JOIN users ON orders.user_id = users.user_id WHERE orders.user_id = ? AND exhibitions.date_end < curdate() ORDER BY exhibitions.date_end ASC LIMIT ?,?",
                user.getUserId(), skip, limit)
                : utilMySqlDao.findAll(
                "SELECT * FROM orders JOIN exhibitions ON orders.exhibition_id = exhibitions.exhibition_id JOIN users ON orders.user_id = users.user_id WHERE orders.user_id = ? AND exhibitions.date_end >= curdate() ORDER BY exhibitions.date_end ASC LIMIT ?,?",
                user.getUserId(), skip, limit);
    }

    @Override
    public List<Integer> countByOrders(List<Exhibition> exhibitions) {
        List<Integer> res = new ArrayList<>();
        for (Exhibition ex: exhibitions) {
            res.add((int) utilMySqlDao.getRowsCount("SELECT COUNT(orders.exhibition_id) FROM orders WHERE orders.exhibition_id = ?", ex.getExhibitionId()));
        }
        return res;
    }


}
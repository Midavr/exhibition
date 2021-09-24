package ua.epam.radchenko.persistence.dao.impl.mysql;

import ua.epam.radchenko.persistence.dao.OrderDao;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.EntityMapper;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.MapperFactory;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.entity.Order;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.persistence.exepion.DaoException;
import ua.epam.radchenko.util.ResourceManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderMySqlDao implements OrderDao {
    private static final String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("order.select.all");
    private static final String INSERT =
            ResourceManager.QUERIES.getProperty("order.insert");
    private static final String UPDATE =
            ResourceManager.QUERIES.getProperty("order.update");
    private static final String DELETE =
            ResourceManager.QUERIES.getProperty("order.delete");
    private static final String COUNT =
            ResourceManager.QUERIES.getProperty("order.count");
    private static final String WHERE_ID =
            ResourceManager.QUERIES.getProperty("order.where.id");
    private static final String WHERE_ACTIVE_AND_USER_ID =
            ResourceManager.QUERIES.getProperty("order.where.active.and.user");
    private static final String WHERE_EXPIRED_AND_USER_ID =
            ResourceManager.QUERIES.getProperty("order.where.expired.and.user");
    private static final String ORDER_BY_END_DATE =
            ResourceManager.QUERIES.getProperty("order.select.order");
    private static final String ORDER_COUNT_ORDERS =
            ResourceManager.QUERIES.getProperty("order.count.orders");


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
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Order> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<Order> findAll(long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
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
                INSERT,
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
                UPDATE + WHERE_ID,
                obj.getExhibitionId().getExhibitionId(),
                obj.getUserId().getUserId(),
                obj.getOrderId());
    }

    @Override
    public void delete(Integer id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }

    @Override
    public long getCount() {
        return utilMySqlDao.getRowsCount(COUNT);
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
                ? utilMySqlDao.getRowsCount(COUNT + WHERE_EXPIRED_AND_USER_ID, user.getUserId())
                : utilMySqlDao.getRowsCount(COUNT + WHERE_ACTIVE_AND_USER_ID, user.getUserId());
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
                SELECT_ALL + WHERE_EXPIRED_AND_USER_ID + ORDER_BY_END_DATE + UtilMySqlDao.LIMIT,
                user.getUserId(), skip, limit)
                : utilMySqlDao.findAll(
                SELECT_ALL + WHERE_ACTIVE_AND_USER_ID + ORDER_BY_END_DATE + UtilMySqlDao.LIMIT,
                user.getUserId(), skip, limit);
    }

    @Override
    public List<Integer> countByOrders(List<Exhibition> exhibitions) {
        List<Integer> res = new ArrayList<>();
        for (Exhibition ex: exhibitions) {
            res.add((int) utilMySqlDao.getRowsCount(ORDER_COUNT_ORDERS, ex.getExhibitionId()));
        }
        return res;
    }


}
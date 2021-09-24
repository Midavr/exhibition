package ua.epam.radchenko.persistence.dao.factory;

import ua.epam.radchenko.persistence.dao.*;
import ua.epam.radchenko.persistence.dao.impl.mysql.*;

public class MySqlDaoFactory extends DaoFactory {
    private ExhibitionDao exhibitionDao;
    private OrderDao orderDao;
    private UserDao userDao;

    public MySqlDaoFactory(){
        this.exhibitionDao = new ExhibitionMySqlDao();
        this.orderDao = new OrderMySqlDao();
        this.userDao = new UserMySqlDao();
    }

    @Override
    public ExhibitionDao getExhibitionDao() {
        return exhibitionDao;
    }

    @Override
    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }
}

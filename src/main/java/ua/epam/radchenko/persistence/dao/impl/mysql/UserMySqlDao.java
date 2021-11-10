package ua.epam.radchenko.persistence.dao.impl.mysql;

import ua.epam.radchenko.persistence.dao.UserDao;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.EntityMapper;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.MapperFactory;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.persistence.exepion.DaoException;
import ua.epam.radchenko.util.ResourceManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserMySqlDao implements UserDao {
    private static final String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("user.select.all");
    private static final String INSERT =
            ResourceManager.QUERIES.getProperty("user.insert");
    private static final String UPDATE =
            ResourceManager.QUERIES.getProperty("user.update");
    private static final String DELETE =
            ResourceManager.QUERIES.getProperty("user.delete");
    private static final String COUNT =
            ResourceManager.QUERIES.getProperty("user.count");
    private static final String WHERE_ID =
            ResourceManager.QUERIES.getProperty("user.where.id");
    private static final String WHERE_LOGIN =
            ResourceManager.QUERIES.getProperty("user.where.login");


    private final UtilMySqlDao<User> utilMySqlDao;

    public UserMySqlDao() {
        this(MapperFactory.getUserMapper());
    }

    public UserMySqlDao(EntityMapper<User> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public UserMySqlDao(UtilMySqlDao<User> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<User> findOne(Integer id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<User> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<User> findAll(long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public List<User> viewAllSorted(long skip, long limit, String sorting, String sortingType, LocalDate sortDate) {
        return null;
    }

    @Override
    public User insert(User obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to insert nullable User");
        }

        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                Integer.class,
                obj.getLogin(),
                obj.getPassword(),
                obj.getRole().toString(),
                obj.getFirstName(),
                obj.getLastName(),
                obj.getDateOfBirth(),
                obj.getGender().toString());
        obj.setUserId(id);

        return obj;
    }

    @Override
    public void update(User obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to update nullable User");
        }

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getLogin(),
                obj.getPassword(),
                obj.getRole().toString(),
                obj.getFirstName(),
                obj.getLastName(),
                obj.getDateOfBirth(),
                obj.getGender().toString(),
                obj.getUserId());
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

    @Override
    public Optional<User> findOneByLogin(String login) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_LOGIN, login);
    }

    @Override
    public boolean existByLogin(String login) {
        return findOneByLogin(login).isPresent();
    }
}


package ua.epam.radchenko.persistence.dao.impl.mysql;

import ua.epam.radchenko.persistence.dao.UserDao;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.EntityMapper;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.MapperFactory;
import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.persistence.exepion.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserMySqlDao implements UserDao {

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
        return utilMySqlDao.findOne("SELECT * FROM users WHERE user_id = ?", id);
    }

    @Override
    public List<User> findAll() {
        return utilMySqlDao.findAll("SELECT * FROM users");
    }

    @Override
    public List<User> findAll(long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return utilMySqlDao.findAll("SELECT * FROM users LIMIT ?,?", skip, limit);
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
                "INSERT INTO users (login, password, role, first_name, last_name, date_of_birth, gender) VALUES(?, ?, ?, ?, ?, ?, ?)",
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
                "UPDATE users SET login = ?, password = ?, role = ?, first_name = ?, last_name = ?, date_of_birth = ?, gender = ? WHERE user_id = ?",
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
                "DELETE FROM users WHERE user_id = ?",
                id);
    }

    @Override
    public long getCount() {
        return utilMySqlDao.getRowsCount("SELECT COUNT(user_id) FROM users");
    }

    @Override
    public long countDateFiltered(LocalDate sortDate) {
        return 0;
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        return utilMySqlDao.findOne("SELECT * FROM users WHERE login = ?", login);
    }

    @Override
    public boolean existByLogin(String login) {
        return findOneByLogin(login).isPresent();
    }
}


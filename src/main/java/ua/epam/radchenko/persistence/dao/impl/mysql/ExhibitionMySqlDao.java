package ua.epam.radchenko.persistence.dao.impl.mysql;

import ua.epam.radchenko.persistence.dao.ExhibitionDao;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.EntityMapper;
import ua.epam.radchenko.persistence.dao.impl.mysql.mapper.MapperFactory;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.persistence.exepion.DaoException;
import ua.epam.radchenko.util.ResourceManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ExhibitionMySqlDao implements ExhibitionDao {
    private static final String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("exhibition.select.all");
    private static final String INSERT =
            ResourceManager.QUERIES.getProperty("exhibition.insert");
    private static final String UPDATE =
            ResourceManager.QUERIES.getProperty("exhibition.update");
    private static final String DELETE =
            ResourceManager.QUERIES.getProperty("exhibition.delete");
    private static final String COUNT =
            ResourceManager.QUERIES.getProperty("exhibition.count");
    private static final String WHERE_ID =
            ResourceManager.QUERIES.getProperty("exhibition.where.id");
    private static final String WHERE_DATE =
            ResourceManager.QUERIES.getProperty("exhibition.where.date");

    private final UtilMySqlDao<Exhibition> utilMySqlDao;

    public ExhibitionMySqlDao() {
        this(MapperFactory.getExhibitionMapper());
    }

    public ExhibitionMySqlDao(EntityMapper<Exhibition> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public ExhibitionMySqlDao(UtilMySqlDao<Exhibition> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Exhibition> findOne(Integer id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Exhibition> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }


    public List<Exhibition> viewAllSorted(long skip, long limit, String sorting, String sortingType, LocalDate sortDate) {
        StringBuilder queryBuilder = new StringBuilder();
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        queryBuilder.append(SELECT_ALL);
        if(sortDate != null){
            queryBuilder.append(WHERE_DATE);
        }
        queryBuilder.append(" ORDER BY ");
        queryBuilder.append(sorting);
        queryBuilder.append(" ");
        queryBuilder.append(sortingType);
        queryBuilder.append(UtilMySqlDao.LIMIT);
        if(sortDate != null){
            return utilMySqlDao.findAll(queryBuilder.toString(), sortDate, sortDate, skip, limit);
        }
        return utilMySqlDao.findAll(queryBuilder.toString(), skip, limit);
    }

    public long countDateFiltered(LocalDate sortDate) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SELECT_ALL);
        if(sortDate != null){
            queryBuilder.append(WHERE_DATE);
            return utilMySqlDao.findAll(queryBuilder.toString(), sortDate, sortDate).size();
        }
        return utilMySqlDao.findAll(queryBuilder.toString()).size();
    }

    public List<Exhibition> findAll(long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Exhibition insert(Exhibition obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to insert nullable Exhibition");
        }
        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                Integer.class,
                obj.getExhibitionName(),
                obj.getDescription(),
                obj.getPrice(),
                obj.getDateStart(),
                obj.getDateEnd(),
                obj.getTheme(),
                obj.getExhibitionStatus().toString(),
                obj.getHall());
        obj.setExhibitionId(id);
        return obj;
    }

    @Override
    public void update(Exhibition obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to update nullable Exhibition");
        }
        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getExhibitionName(),
                obj.getDescription(),
                obj.getPrice(),
                obj.getDateStart(),
                obj.getDateEnd(),
                obj.getTheme(),
                obj.getExhibitionStatus().toString(),
                obj.getHall(),
                obj.getExhibitionId());
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
}

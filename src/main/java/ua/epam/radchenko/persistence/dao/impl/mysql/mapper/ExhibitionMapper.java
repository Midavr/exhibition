package ua.epam.radchenko.persistence.dao.impl.mysql.mapper;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.util.type.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExhibitionMapper implements EntityMapper<Exhibition> {
    private static final String EXHIBITION_ID_FIELD = "exhibition_id";
    private static final String EXHIBITION_NAME_FIELD = "exhibition_name";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String PRICE_FIELD = "price";
    private static final String DATE_START_FIELD = "date_start";
    private static final String DATE_END_FIELD = "date_end";
    private static final String THEME_FIELD = "theme";
    private static final String EXHIBITION_STATUS_FIELD = "exhibition_status";
    private static final String EXHIBITION_HALL_FIELD = "hall";

    @Override
    public Exhibition mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        return Exhibition.newBuilder()
                .setExhibitionId(resultSet.getInt(tablePrefix + EXHIBITION_ID_FIELD))
                .setExhibitionName((resultSet.getString(tablePrefix + EXHIBITION_NAME_FIELD)))
                .setDescription(resultSet.getString(tablePrefix + DESCRIPTION_FIELD))
                .setPrice(resultSet.getBigDecimal(tablePrefix + PRICE_FIELD))
                .setDateStart(resultSet.getObject(tablePrefix + DATE_START_FIELD, LocalDate.class))
                .setDateEnd(resultSet.getObject(tablePrefix + DATE_END_FIELD, LocalDate.class))
                .setTheme(resultSet.getString(tablePrefix + THEME_FIELD))
                .setExhibitionStatus(Status.valueOf(resultSet.getString(tablePrefix + EXHIBITION_STATUS_FIELD)))
                .setHall(resultSet.getInt(tablePrefix + EXHIBITION_HALL_FIELD))
                .build();
    }

}

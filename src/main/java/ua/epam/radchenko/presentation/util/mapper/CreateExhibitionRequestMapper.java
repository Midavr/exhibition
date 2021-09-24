package ua.epam.radchenko.presentation.util.mapper;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;
import ua.epam.radchenko.util.type.Status;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExhibitionRequestMapper implements RequestEntityMapper<Exhibition> {

    @Override
    public Exhibition mapToObject(HttpServletRequest request) {
        return Exhibition.newBuilder()
                .setExhibitionName(request.getParameter(RequestParameters.EXHIBITION_NAME))
                .setDescription(request.getParameter(RequestParameters.EXHIBITION_DESCRIPTION))
                .setPrice(new BigDecimal(request.getParameter(RequestParameters.EXHIBITION_PRICE)))
                .setDateStart(LocalDate.parse(request.getParameter(RequestParameters.EXHIBITION_DATE_START)))
                .setDateEnd(LocalDate.parse(request.getParameter(RequestParameters.EXHIBITION_DATE_END)))
                .setTheme(request.getParameter(RequestParameters.EXHIBITION_THEME))
                .setExhibitionStatus(Status.valueOf(request.getParameter(RequestParameters.EXHIBITION_STATUS).toUpperCase()))
                .setHall(Integer.valueOf(request.getParameter(RequestParameters.EXHIBITION_HALL)))
                .build();
    }
}

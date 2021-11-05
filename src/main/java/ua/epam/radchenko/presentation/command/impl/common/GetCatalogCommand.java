package ua.epam.radchenko.presentation.command.impl.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.PaginationManager;
import ua.epam.radchenko.presentation.util.Sorting;
import ua.epam.radchenko.presentation.util.SortingType;
import ua.epam.radchenko.presentation.util.Util;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetCatalogCommand implements Command {
    private final static long RECORDS_PER_PAGE = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCatalogCommand.class);
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final ExhibitionService exhibitionService = context.getBean("exhibitionService", ExhibitionService.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        PaginationManager paginationManager = new PaginationManager(RECORDS_PER_PAGE);

        String pageWay = request.getParameter(RequestParameters.PAGINATION_WAY);
        String pageType = request.getParameter(RequestParameters.PAGINATION_TYPE);
        LocalDate sortDate = null;


        if(request.getParameter(RequestParameters.PAGINATION_SORT_DATE) != null){
            try {
                sortDate = LocalDate.parse(request.getParameter(RequestParameters.PAGINATION_SORT_DATE));
            } catch (DateTimeParseException e) {
                LOGGER.debug("Date wrong value", e);
            }
        }

        SortingType sortingType = SortingType.EXHIBITION_ID;
        Sorting sorting = Sorting.DESC;

        if(pageWay != null){
            pageWay = pageWay.toLowerCase(Locale.ROOT);
            if(pageWay.equals("asc")){
                sorting = Sorting.ASC;
            }else{
                sorting = Sorting.DESC;
            }
        }

        if(pageType != null){
            pageType = pageType.toLowerCase(Locale.ROOT);
            if(pageType.equals("price")){
                sortingType = SortingType.PRICE;
            }else if (pageType.equals("theme")){
                sortingType = SortingType.THEME;
            }else {
                sortingType = SortingType.EXHIBITION_ID;
            }
        }

        long rowsCount = exhibitionService.getExhibitionDateFilteredCount(sortDate);
        long skip = paginationManager.manage(request, rowsCount);

        List<Exhibition> exhibitions = exhibitionService.findAllExhibitionsSorted(skip, paginationManager.getRecordsPerPage(), sortingType.getValue(), sorting.getType(), sortDate);
        List<Boolean> currentDate = new ArrayList<>();
        for (Exhibition ex: exhibitions) {
            currentDate.add(ex.getDateEnd().isBefore(LocalDate.now()));
        }

        request.setAttribute(Attributes.CATALOG, exhibitions);
        request.setAttribute("activeSortingWay", sorting);
        request.setAttribute("activeSortingType", sortingType);
        request.setAttribute("activeSortingDate", sortDate);
        request.setAttribute("isExpired", currentDate);
        Util.checkErrorParameter(request, RequestParameters.ERROR_ATTRIBUTE);
        return CommandResult.forward(Views.CATALOG_VIEW);
    }



}

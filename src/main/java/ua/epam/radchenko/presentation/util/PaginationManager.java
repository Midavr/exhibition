package ua.epam.radchenko.presentation.util;

import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;

/**
 * Class that is needed to carry information for views that do objects pagination
 */
public class PaginationManager {
    private static final long DEFAULT_RECORDS_PER_PAGE = 10;
    private static final String DEFAULT_REQUEST_PARAMETER_PAGINATION_PAGE = RequestParameters.PAGINATION_PAGE;
    private static final String DEFAULT_ATTRIBUTE_PAGINATION_PAGE = Attributes.PAGINATION_PAGE;
    private static final String DEFAULT_ATTRIBUTE_PAGINATION_NUMBER_OF_PAGES = Attributes.PAGINATION_NUMBER_OF_PAGES;
    private static final int FIRST_PAGE = 1;

    private final long recordsPerPage;
    private final String requestParameterPaginationPage;
    private final String attributesPaginationPage;
    private final String attributesPaginationNumberOfPage;

    public PaginationManager() {
        this(DEFAULT_RECORDS_PER_PAGE,
                DEFAULT_REQUEST_PARAMETER_PAGINATION_PAGE,
                DEFAULT_ATTRIBUTE_PAGINATION_PAGE,
                DEFAULT_ATTRIBUTE_PAGINATION_NUMBER_OF_PAGES);
    }

    public PaginationManager(long recordsPerPage) {
        this(recordsPerPage,
                DEFAULT_REQUEST_PARAMETER_PAGINATION_PAGE,
                DEFAULT_ATTRIBUTE_PAGINATION_PAGE,
                DEFAULT_ATTRIBUTE_PAGINATION_NUMBER_OF_PAGES);
    }

    public PaginationManager(long recordsPerPage,
                             String requestParameterPaginationPage,
                             String attributesPaginationPage,
                             String attributesPaginationNumberOfPage) {
        this.recordsPerPage = recordsPerPage;
        this.requestParameterPaginationPage = requestParameterPaginationPage;
        this.attributesPaginationPage = attributesPaginationPage;
        this.attributesPaginationNumberOfPage = attributesPaginationNumberOfPage;
    }

    public long manage(HttpServletRequest req, long rowsCount) {
        String pageStr = req.getParameter(requestParameterPaginationPage);


        long numberOfPages = rowsCount / recordsPerPage;
        if (rowsCount % recordsPerPage > 0) {
            numberOfPages++;
        }
        long page;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException ex) {
            page = FIRST_PAGE;
        }
        if (page <= 0) {
            page = FIRST_PAGE;
        }
        if (numberOfPages > 0 && page > numberOfPages) {
            page = numberOfPages;
        }
        long skip = (page - 1) * recordsPerPage;
        req.setAttribute(attributesPaginationPage, page);
        req.setAttribute(attributesPaginationNumberOfPage, numberOfPages);
        return skip;
    }

    public long getRecordsPerPage() {
        return recordsPerPage;
    }
}

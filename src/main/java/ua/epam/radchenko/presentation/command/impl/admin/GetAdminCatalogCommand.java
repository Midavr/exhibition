package ua.epam.radchenko.presentation.command.impl.admin;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.PaginationManager;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.service.OrderService;
import ua.epam.radchenko.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAdminCatalogCommand implements Command {
    private final ExhibitionService exhibitionService =
            ServiceFactory.getExhibitionService();

    private final OrderService orderService =
            ServiceFactory.getOrderService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        PaginationManager paginationManager = new PaginationManager();
        long rowsCount = exhibitionService.getExhibitionCount();
        long skip = paginationManager.manage(request, rowsCount);
        List<Exhibition> exhibitions = exhibitionService.findAllExhibitions(
                skip, paginationManager.getRecordsPerPage());
        List<Integer> exhibitionVisits = orderService.countExhibitionsOrders(exhibitions);


        request.setAttribute(Attributes.CATALOG, exhibitions);
        request.setAttribute(Attributes.CATALOG, exhibitions);
        request.setAttribute("visits", exhibitionVisits);
        return CommandResult.forward(Views.ADMIN_CATALOG_VIEW);
    }
}

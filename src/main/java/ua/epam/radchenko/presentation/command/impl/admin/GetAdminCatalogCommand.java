package ua.epam.radchenko.presentation.command.impl.admin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.radchenko.SpringConfig;
import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.presentation.command.Command;
import ua.epam.radchenko.presentation.command.CommandResult;
import ua.epam.radchenko.presentation.util.PaginationManager;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.Views;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAdminCatalogCommand implements Command {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    private final ExhibitionService exhibitionService = context.getBean("exhibitionServiceImpl", ExhibitionService.class);
    private final OrderService orderService = context.getBean("orderServiceImpl", OrderService.class);

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

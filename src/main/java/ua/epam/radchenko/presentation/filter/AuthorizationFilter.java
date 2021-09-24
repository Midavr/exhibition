package ua.epam.radchenko.presentation.filter;

import ua.epam.radchenko.persistence.entity.User;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.constants.PagesPaths;
import ua.epam.radchenko.util.type.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The filter restricts access for non-authorized users.
 */
public class AuthorizationFilter implements Filter {
    private static final Set<String> secureAdminPaths = new HashSet<>();
    private static final Set<String> secureUserPaths = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        secureAdminPaths.add(PagesPaths.ADMIN_CATALOG_PATH);
        secureAdminPaths.add(PagesPaths.CREATE_EXHIBITION_PATH);
        secureAdminPaths.add(PagesPaths.CHANGE_STATUS_EXHIBITION_PATH);

        secureUserPaths.add(PagesPaths.CART_TICKETS_PAYMENT_PATH);
        secureUserPaths.add(PagesPaths.ORDERS_PATH);
        secureUserPaths.add(PagesPaths.CART_ADD_ITEM_PATH);
        secureUserPaths.add(PagesPaths.CART_REMOVE_ITEM_PATH);
        secureUserPaths.add(PagesPaths.CART_REMOVE_ALL_ITEM_PATH);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        User user = Objects.nonNull(session)
                ? (User) session.getAttribute(Attributes.USER)
                : null;
        boolean isUser = false;
        boolean isAdmin = false;

        if(user != null){
            isUser = Role.USER.equals(user.getRole());
            isAdmin = Role.ADMIN.equals(user.getRole());
        }

        boolean isOnlyAdminRequest = secureAdminPaths.contains(req.getPathInfo());
        boolean isOnlyUserRequest = secureUserPaths.contains(req.getPathInfo());

        if (isOnlyAdminRequest) {
            doFilter(isAdmin, req, resp, chain);
        } else if (isOnlyUserRequest) {
            doFilter(isUser, req, resp, chain);
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void doFilter(boolean isAuthorized,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain chain) throws IOException, ServletException {
        if (isAuthorized) {
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}

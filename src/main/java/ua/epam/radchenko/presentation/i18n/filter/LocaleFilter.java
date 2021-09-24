package ua.epam.radchenko.presentation.i18n.filter;

import ua.epam.radchenko.presentation.i18n.SupportedLocale;
import ua.epam.radchenko.presentation.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    private static final String LANG = "lang";
    private static final String LOCALE = "locale";

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getParameter(LANG) != null) {
            replaceUserLocale(req);
            String referer = Util.getReferer(req);
            Util.redirectTo(req, res, referer);
            return;
        }

        if (req.getSession().getAttribute(LOCALE) == null) {
            setUserLocale(req);
        }
        chain.doFilter(request, response);
    }

    private void replaceUserLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String langParameter = request.getParameter(LANG);
        Locale locale = SupportedLocale.getLocaleOrDefault(langParameter);
        session.setAttribute(LOCALE, locale);
    }

    private void setUserLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = SupportedLocale.getDefault();
        session.setAttribute(LOCALE, locale);
    }
}

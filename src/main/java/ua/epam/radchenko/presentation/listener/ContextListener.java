package ua.epam.radchenko.presentation.listener;

import ua.epam.radchenko.presentation.i18n.SupportedLocale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final String SUPPORTED_LOCALES = "supportedLocales";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute(SUPPORTED_LOCALES,
                SupportedLocale.getSupportedLanguages());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}

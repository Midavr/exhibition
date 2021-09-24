package ua.epam.radchenko.util;

import java.util.ResourceBundle;

public enum ResourceManager {
    DATABASE(ResourceBundle.getBundle("properties.database")),
    QUERIES(ResourceBundle.getBundle("properties.mysql_queries")),
    PATH(ResourceBundle.getBundle("properties.path")),
    VIEW(ResourceBundle.getBundle("properties.view")),
    ATTRIBUTE(ResourceBundle.getBundle("properties.attribute")),
    PARAMETER(ResourceBundle.getBundle("properties.parameter"));

    private ResourceBundle resourceBundle;

    ResourceManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Gets a string for the given key from this resource bundle
     *
     * @param key the key for the desired string
     * @return the string for the given key
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

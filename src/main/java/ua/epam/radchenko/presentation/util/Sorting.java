package ua.epam.radchenko.presentation.util;

/**
 * Enumeration that represents data sorting way.
 */
public enum Sorting {
    DESC("DESC"),
    ASC("ASC"),
    DEFAULT("");

    private String type;

    Sorting(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

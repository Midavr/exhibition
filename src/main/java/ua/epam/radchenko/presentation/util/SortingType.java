package ua.epam.radchenko.presentation.util;

public enum SortingType {
    EXHIBITION_ID("exhibition_id"),
    PRICE("price"),
    THEME("theme");

    private String value;

    private SortingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Provides a safe SortingType object.
     *
     * @param value of type that you want to get
     * @return enumeration item in safe SortingType object
     */
    public static SortingType safeValueOf(final String value) {
        try {
            return SortingType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return EXHIBITION_ID;
        }
    }
}
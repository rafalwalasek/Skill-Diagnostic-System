package com.rafal.skilldiagnosticsystem.model;

public enum Category {
    JAVA("Java"),
    SPRING("Spring"),
    BAZY_DANYCH("Bazy danych"),
    PROJEKTOWANIE_APLIKACJI("Projektowanie aplikacji");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return displayName;
    }
}

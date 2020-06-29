package ru.netology.test.model;

public enum EventType {
    MOVIE("Movie"), THEATER("Theater"), CONCERT("Concert");

    private final String displayValue;

    EventType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

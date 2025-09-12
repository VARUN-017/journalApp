package com.aimers.journal_app.constants;

public enum Placeholders {

    API_KEY("<apiKey>"),

    WEATHER_API("weather_api"),
    CITY("<city>");

    private final String value;

    Placeholders(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}

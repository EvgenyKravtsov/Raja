package com.example.forecaster.domain.model;

public final class Forecast {

    private final String date;
    private final String temperature;

    ////

    public Forecast(String date, String temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    ////

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }
}

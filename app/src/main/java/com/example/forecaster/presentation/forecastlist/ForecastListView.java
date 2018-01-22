package com.example.forecaster.presentation.forecastlist;

import com.example.forecaster.domain.model.Forecast;

import java.util.List;

public interface ForecastListView {

    void display(List<Forecast> forecasts);

    void displayProgressBar();

    void hideProgressBar();
}

package com.example.forecaster.presentation.forecastlist;

import com.example.forecaster.domain.ForecastProvider;
import com.example.forecaster.domain.model.Forecast;

import java.util.List;

final class ForecastListPresenter implements ForecastProvider.Listener {

    private final ForecastListView view;
    private final ForecastProvider forecastProvider;

    ////

    ForecastListPresenter(ForecastListView view, ForecastProvider forecastProvider) {
        this.view = view;
        this.forecastProvider = forecastProvider;
    }

    ////

    void onViewDisplayed() {
        forecastProvider.setForecastProviderListener(this);
        forecastProvider.requestForecasts();
        view.displayProgressBar();
    }

    //// FORECAST PROVIDER LISTENER

    @Override
    public void onForecastsReady(List<Forecast> forecasts) {
        view.hideProgressBar();
        view.display(forecasts);
    }
}

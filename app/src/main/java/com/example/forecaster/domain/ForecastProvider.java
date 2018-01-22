package com.example.forecaster.domain;

import com.example.forecaster.domain.model.Forecast;

import java.util.List;

public interface ForecastProvider {

    interface Listener {

        void onForecastsReady(List<Forecast> forecasts);
    }

    ////

    void setForecastProviderListener(Listener listener);

    void requestForecasts();
}

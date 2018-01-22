package com.example.forecaster.presentation.forecastlist;

import com.example.forecaster.domain.ForecastProvider;
import com.example.forecaster.domain.model.Forecast;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public final class ForecastListPresenterTest {

    private ForecastListPresenter presenter;

    @Mock
    private ForecastListView viewMock;
    @Mock
    private ForecastProvider forecastProviderMock;

    ////

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ForecastListPresenter(viewMock, forecastProviderMock);
    }

    ////

    @Test
    public void onViewDisplayed_subscribedForForecastProviderUpdates() {
        presenter.onViewDisplayed();

        verify(forecastProviderMock).setForecastProviderListener(presenter);
    }

    @Test
    public void onViewDisplayed_forecastsReceivedFromProvider() {
        presenter.onViewDisplayed();

        verify(forecastProviderMock).requestForecasts();
    }

    @Test
    public void onViewDisplayed_progressBarDisplayed() {
        presenter.onViewDisplayed();

        verify(viewMock).displayProgressBar();
    }

    @Test
    public void onForecastObtained_forecastsDisplayed() {
        List<Forecast> forecasts = new ArrayList<>();

        presenter.onForecastsReady(forecasts);

        verify(viewMock).display(forecasts);
    }

    @Test
    public void onForecastObtained_progressBarHidden() {
        List<Forecast> forecasts = new ArrayList<>();

        presenter.onForecastsReady(forecasts);

        verify(viewMock).hideProgressBar();
    }
}

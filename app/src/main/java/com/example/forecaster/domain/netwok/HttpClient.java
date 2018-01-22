package com.example.forecaster.domain.netwok;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.forecaster.domain.ForecastProvider;
import com.example.forecaster.domain.model.Forecast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public final class HttpClient implements ForecastProvider {

    private static final String BASE_URL = "https://api.darksky.net/forecast/";
    private static final String DARK_SKY_API_KEY = "185a8d3be5c00feee9330161d7b07495";
    private static final String POSITION = "55.751244,37.618423";
    private static HttpClient instance;

    private final DarkSkyApi darkSkyApi;

    @Nullable
    private ForecastProvider.Listener listener;

    ////

    private HttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        darkSkyApi = retrofit.create(DarkSkyApi.class);
    }

    public static HttpClient getInstance() {
        if (instance == null) instance = new HttpClient();
        return instance;
    }

    //// FORECAST PROVIDER

    @Override
    public void setForecastProviderListener(ForecastProvider.Listener listener) {
        this.listener = listener;
    }

    @Override
    public void requestForecasts() {
        Call<ResponseBody> call = darkSkyApi.requestForecasts(DARK_SKY_API_KEY, POSITION);
        final List<Forecast> forecasts = new ArrayList<>();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(
                    @NonNull Call<ResponseBody> call,
                    @NonNull retrofit2.Response<ResponseBody> response
            ) {
                try {
                    ResponseBody responseBody = response.body();

                    if (responseBody != null) {
                        forecasts.addAll(
                                new DarkSkyParser().parseDailyForecasts(responseBody.string())
                        );

                        if (listener != null) listener.onForecastsReady(forecasts);
                    }
                    else {
                        Log.d("TAG", "onResponse: NULL BODY");
                    }
                }
                catch (IOException e) {
                    Log.d("TAG", "onResponse: IOException");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}

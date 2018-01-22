package com.example.forecaster.presentation.forecastlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.forecaster.R;
import com.example.forecaster.domain.model.Forecast;

import butterknife.BindView;
import butterknife.ButterKnife;

final class ForecastViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.date_text_view)
    TextView dateTextView;
    @BindView(R.id.temperature_text_view)
    TextView temperatureTextView;

    ////

    ForecastViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    ////

    void bind(Forecast forecast) {
        dateTextView.setText(forecast.getDate());
        temperatureTextView.setText(forecast.getTemperature());
    }
}






























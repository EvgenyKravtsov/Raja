package com.example.forecaster.presentation.forecastlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forecaster.R;
import com.example.forecaster.domain.model.Forecast;

import java.util.List;

final class ForecastRecyclerAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private final List<Forecast> forecasts;

    ////

    ForecastRecyclerAdapter(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    //// RECYCLER ADAPTER

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_item_forecast,
                parent,
                false
        );

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }
}




















package com.example.forecaster.presentation.forecastlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.forecaster.R;
import com.example.forecaster.domain.model.Forecast;
import com.example.forecaster.domain.netwok.HttpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class ForecastListFragment extends Fragment implements ForecastListView {

    @BindView(R.id.forecast_recycler_view)
    RecyclerView forecastRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    ////

    private ForecastListPresenter presenter;

    //// FRAGMENT


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ForecastListPresenter(this, HttpClient.getInstance());
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_forecast_list, container, false);
        ButterKnife.bind(this, view);
        setupForecastRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onViewDisplayed();
    }

    //// FORECAST LIST VIEW

    @Override
    public void display(List<Forecast> forecasts) {
        forecastRecyclerView.setAdapter(new ForecastRecyclerAdapter(forecasts));
    }

    @Override
    public void displayProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    ////

    private void setupForecastRecyclerView() {
        forecastRecyclerView.setHasFixedSize(true);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}





























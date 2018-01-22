package com.example.forecaster.domain.netwok;

import android.util.Log;

import com.example.forecaster.domain.model.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

final class DarkSkyParser {

    List<Forecast> parseDailyForecasts(String responseString) {
        try {
            JSONObject responseJson = new JSONObject(responseString);
            JSONObject dailyJson = responseJson.getJSONObject("daily");
            JSONArray dataJson = dailyJson.getJSONArray("data");

            List<Forecast> forecasts = new ArrayList<>();

            for (int i = 0; i < dataJson.length(); i++) {
                JSONObject itemJson = dataJson.getJSONObject(i);
                forecasts.add(
                        new Forecast(
                                createDateStringFromUnixTimestamp(itemJson.getLong("time")),
                                itemJson.getString("windGust")
                        )
                );
            }

            return forecasts;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    ////

    private String createDateStringFromUnixTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);

        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM", Locale.ROOT);
        return dateFormat.format(calendar.getTime());
    }
}

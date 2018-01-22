package com.example.forecaster.domain.netwok;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface DarkSkyApi {

    // GET https://api.darksky.net/forecast/0123456789abcdef9876543210fedcba/42.3601,-71.0589

    @GET("{key}/{position}")
    Call<ResponseBody> requestForecasts(@Path("key") String key, @Path("position") String position);
}

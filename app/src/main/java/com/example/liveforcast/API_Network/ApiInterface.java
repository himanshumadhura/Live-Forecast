package com.example.liveforcast.API_Network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
        @GET("forecast.json")
        Call<ModelClass> getJson (@Query("key") String apiKey, @Query("q") String Query, @Query("days") String Days);
}

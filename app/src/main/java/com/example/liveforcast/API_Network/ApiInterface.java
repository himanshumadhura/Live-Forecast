package com.example.liveforcast.API_Network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
        @GET("current")
        Call<ModelClass> getJson (@Query("access_key") String apiKey, @Query("query") String Query);
}

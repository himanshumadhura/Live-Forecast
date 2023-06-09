package com.example.liveforcast.API_Network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private String api = "https://api.weatherapi.com/v1/";
    public static RetrofitInstance instance;
    public static ApiInterface apiInterface;

    RetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static RetrofitInstance getInstance(){
        if(instance == null){
            instance = new RetrofitInstance();
        }
        return instance;
    }

}

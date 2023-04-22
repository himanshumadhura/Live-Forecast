package com.example.liveforcast.API_Network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private String api = "http://api.weatherstack.com/";
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

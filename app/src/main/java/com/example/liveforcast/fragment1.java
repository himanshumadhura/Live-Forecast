package com.example.liveforcast;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.liveforcast.API_Network.ModelClass;
import com.example.liveforcast.API_Network.RetrofitInstance;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment1 extends Fragment {
    private TextView hour, temp_hr;
    private String apiKey, hourly;
    int i;

    public fragment1(String apiKey, int i) {
        this.apiKey = apiKey;
        this.i=i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        hour = view.findViewById(R.id.hour);
        temp_hr = view.findViewById(R.id.hour_temp);
        RetrofitInstance.getInstance();
        RetrofitInstance.apiInterface.getJson(apiKey,"Amritsar","3").enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {

                String imageCode = response.body().getForecast().getForecastday().get(0).getHour().get(i).getCondition_hour().getIcon().substring(35);
                Log.e("ICON", (imageCode));


                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                storageRef.child("weather_hourly_icons/" + imageCode).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                Log.e("URL", String.valueOf(uri));
                        ImageView imgIcon = view.findViewById(R.id.icon_hour);
                        Glide.with(fragment1.this).load(uri).into(imgIcon);
                    }
                });

                hour.setText(response.body().getForecast().getForecastday().get(0).getHour().get(i).getTime().substring(11));
                temp_hr.setText(response.body().getForecast().getForecastday().get(0).getHour().get(i).getTemp_c() + "°C");
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                Log.e("api", t.getLocalizedMessage());
            }
        });
        return view;
    }
}
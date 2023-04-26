package com.example.liveforcast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.example.liveforcast.API_Network.ApiInterface;
import com.example.liveforcast.API_Network.ModelClass;
import com.example.liveforcast.API_Network.RetrofitInstance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreen extends AppCompatActivity {

    FusedLocationProviderClient flpc;
    Geocoder geocoder;
    int PERMISSION_ID = 44;
    double longitude, latitude;
    String City;
    TextView cityName, temp, weather_des, feels_like_temp, humidity_per, wind_dir, wind_speed, uv_detail, visibility_detail, air_pressure, precip_detail, cloud_cover_detail;
    ConstraintLayout wt_layout;
    Loading loadGif;
    private String apiKey = "5f539f194620d4d29cccd756d311648c";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        loadGif = new Loading(this);
        loadGif.showDialog();

        cityName = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        weather_des = findViewById(R.id.weather_des);
        feels_like_temp = findViewById(R.id.feel_like_temp);
        humidity_per = findViewById(R.id.humidity_per);
        wind_dir = findViewById(R.id.wind_dir);
        wind_speed = findViewById(R.id.wind_speed);
        uv_detail = findViewById(R.id.uv_detail);
        visibility_detail = findViewById(R.id.visibility_detail);
        air_pressure = findViewById(R.id.air_pressure);
        wt_layout = findViewById(R.id.wt_layout);
        precip_detail = findViewById(R.id.precip_detail);
        cloud_cover_detail = findViewById(R.id.cloud_cover_detail);

        flpc = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());
        getLastLocation();
    }

    public void getLastLocation() {
        if (CheckPermission()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                flpc.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location loc = task.getResult();
                        if (loc == null) {
                            Log.d("Res", String.valueOf(loc));
                        } else {
                            longitude = loc.getLongitude();
                            latitude = loc.getLatitude();
                            try{
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                City = addresses.get(0).getLocality();
                                getData();
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
               Toast.makeText(HomeScreen.this, "Loaction is disabled. Please turn it on...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        }else{
            requestPermissions();
        }
    }

    public boolean isLocationEnabled(){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public boolean CheckPermission(){
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_ID){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(CheckPermission()){
            getLastLocation();
        }
    }

    public void getData(){
        RetrofitInstance.getInstance();

        RetrofitInstance.apiInterface.getJson(apiKey, String.valueOf(City)).enqueue(new Callback<ModelClass>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ModelClass> call, @NonNull Response<ModelClass> response) {
                assert response.body() != null;

                Log.e("api", "onResponse : " + response.body().getRequest().getType() + " ----> " + response.body().getRequest().getQuery());

                cityName.setText(String.valueOf(response.body().getLocation().getName()));
                temp.setText(String.valueOf(response.body().getCurrent().getTemperature()));
                weather_des.setText(" " + response.body().getCurrent().getWeather_descriptions().get(0));
                feels_like_temp.setText((response.body().getCurrent().getFeelslike()) + "°C");
                humidity_per.setText((response.body().getCurrent().getHumidity()) + "%");
                wind_dir.setText(response.body().getCurrent().getWind_dir() + " Wind");
                wind_speed.setText((response.body().getCurrent().getWind_speed()) + " Km/h");
                uv_detail.setText(String.valueOf(response.body().getCurrent().getUv_index()));
                visibility_detail.setText((response.body().getCurrent().getVisibility()) + " Km");
                air_pressure.setText((response.body().getCurrent().getPressure()) + " hPa");
                precip_detail.setText((response.body().getCurrent().getPrecip()) + "%");
                cloud_cover_detail.setText((response.body().getCurrent().getCloudcover() + "%"));

                wt_layout.setVisibility(View.VISIBLE);
                loadGif.hideDialog();
            }

            @Override
            public void onFailure(@NonNull Call<ModelClass> call, Throwable t) {
                Log.e("api", "OnFailure : " + t.getLocalizedMessage());
            }
        });
    }
}


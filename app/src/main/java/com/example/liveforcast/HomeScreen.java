package com.example.liveforcast;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.liveforcast.API_Network.ApiInterface;
import com.example.liveforcast.API_Network.ModelClass;
import com.example.liveforcast.API_Network.RetrofitInstance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

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
    String City, typeName, cityName;

    TextView type;
    TextView city;

    private String apiKey = "5f539f194620d4d29cccd756d311648c";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        type = findViewById(R.id.type);
        city = findViewById(R.id.city);

        flpc = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());
        getLastLocation();
        getData();

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
            @Override
            public void onResponse(@NonNull Call<ModelClass> call, @NonNull Response<ModelClass> response) {
                assert response.body() != null;
                typeName = response.body().getRequest().getType();
                cityName = response.body().getRequest().getQuery();

                type.setText(typeName);
                city.setText(cityName);

                Log.e("api", "onResponse : " + response.body().getRequest().getType() + " ----> " + response.body().getRequest().getQuery());
            }

            @Override
            public void onFailure(@NonNull Call<ModelClass> call, Throwable t) {
                Log.e("api", "OnFailure : " + t.getLocalizedMessage());
            }
        });
    }
}


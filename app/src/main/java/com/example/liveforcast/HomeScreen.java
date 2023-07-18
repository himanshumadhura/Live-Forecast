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
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.liveforcast.API_Network.ModelClass;
import com.example.liveforcast.API_Network.RetrofitInstance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {

    String hour_detail;

    FusedLocationProviderClient flpc;
    Geocoder geocoder;
    int PERMISSION_ID = 44;
    double longitude, latitude;
    String City, hr;
    TextView cityName, temp, weather_des, feels_like_temp, humidity_per, wind_dir, wind_speed, uv_detail, visibility_detail, air_pressure, precip_detail, cloud_cover_detail, max_min_temp;
    ConstraintLayout wt_layout;
    Loading loadGif;
    private final String apiKey = "f6daa7c697944f61bee95421232804";

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
        max_min_temp = findViewById(R.id.max_min_temp);
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

        for (int i = 0; i < 24; i++) {

            FragmentManager frm = getSupportFragmentManager();
            FragmentTransaction ft = frm.beginTransaction();
            ft.add(R.id.frag1, new fragment1(apiKey, i));
            ft.commit();
        }


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
                            requestNewLocationData();
                        } else {
                            longitude = loc.getLongitude();
                            latitude = loc.getLatitude();
                            try {
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                City = addresses.get(0).getLocality();
                                getData();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(HomeScreen.this, "Location is disabled. Please turn it on...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        } else {
            requestPermissions();
        }
    }

    public void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        flpc = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        flpc.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    public LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                City = addresses.get(0).getLocality();
                getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public boolean isLocationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public boolean CheckPermission() {
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CheckPermission()) {
            getLastLocation();
        }
    }

    public void getData() {
        RetrofitInstance.getInstance();

        if (RetrofitInstance.apiInterface.getJson(apiKey, String.valueOf(City), "3") != null) {
            Log.e("Himanshu-IF", "IF Statement Working");
            RetrofitInstance.apiInterface.getJson(apiKey, String.valueOf(City), "3").enqueue(new Callback<ModelClass>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                    assert response.body() != null;

                    double uv = response.body().getCurrent().getUv();
                    String uv_des;
                    if (uv > 0 & uv <= 3) {
                        uv_des = "Low";
                    } else if (uv > 3 & uv <= 6) {
                        uv_des = "Moderate";
                    } else if (uv > 6 & uv <= 9) {
                        uv_des = "High";
                    } else {
                        uv_des = "Very High";
                    }

                    hr = response.body().getForecast().getForecastday().get(0).getHour().get(0).getTime();
                    Log.d("Himanshu", hr);

                    cityName.setText(String.valueOf(response.body().getLocation().getName()));
                    temp.setText(String.valueOf(response.body().getCurrent().getTemp_c()));
                    weather_des.setText(" " + response.body().getCurrent().getCondition().getText());
                    max_min_temp.setText("Max/Min: " + response.body().getForecast().getForecastday().get(0).getDay().getMaxtemp_c() + "/" + response.body().getForecast().getForecastday().get(0).getDay().getMintemp_c() + "°C");

                    feels_like_temp.setText((response.body().getCurrent().getFeelslike_c()) + "°C");
                    humidity_per.setText((response.body().getCurrent().getHumidity()) + "%");
                    wind_dir.setText(response.body().getCurrent().getWind_dir() + " Wind");
                    wind_speed.setText((response.body().getCurrent().getWind_kph()) + " Km/h");
                    uv_detail.setText(uv_des);
                    visibility_detail.setText((response.body().getCurrent().getVis_km()) + " Km");
                    air_pressure.setText((response.body().getCurrent().getPressure_mb()) + " hPa");
                    precip_detail.setText((response.body().getCurrent().getPrecip_mm()) + " mm");
                    cloud_cover_detail.setText((response.body().getCurrent().getCloud() + "%"));
                    hour_detail = response.body().getForecast().getForecastday().get(0).getHour().get(0).getTime();
                    Log.e("HOUR", hour_detail);

                    wt_layout.setVisibility(View.VISIBLE);
                    loadGif.hideDialog();
                }

                @Override
                public void onFailure(Call<ModelClass> call, Throwable t) {
                    Log.e("api", "OnFailure : " + t.getLocalizedMessage());
                }
            });

//----------------------------------------------------------------------------------------------------------------------------------------------

        } else {
            Log.e("Himanshu-IF", "ELSE Statement Working");
            RetrofitInstance.apiInterface.getJson(apiKey, (latitude + "," + longitude), "3").enqueue(new Callback<ModelClass>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<ModelClass> call, @NonNull Response<ModelClass> response) {
                    assert response.body() != null;

                    double uv = response.body().getCurrent().getUv();
                    String uv_des;
                    if (uv > 0 & uv <= 3) {
                        uv_des = "Low";
                    } else if (uv > 3 & uv <= 6) {
                        uv_des = "Moderate";
                    } else if (uv > 6 & uv <= 9) {
                        uv_des = "High";
                    } else {
                        uv_des = "Very High";
                    }

                    hr = response.body().getForecast().getForecastday().get(0).getHour().get(0).getTime();
                    Log.d("Himanshu", hr);

                    cityName.setText(String.valueOf(response.body().getLocation().getName()));
                    temp.setText(String.valueOf(response.body().getCurrent().getTemp_c()));
                    weather_des.setText(" " + response.body().getCurrent().getCondition().getText());
                    max_min_temp.setText("Max/Min: " + response.body().getForecast().getForecastday().get(0).getDay().getMaxtemp_c() + "/" + response.body().getForecast().getForecastday().get(0).getDay().getMintemp_c() + "°C");

                    feels_like_temp.setText((response.body().getCurrent().getFeelslike_c()) + "°C");
                    humidity_per.setText((response.body().getCurrent().getHumidity()) + "%");
                    wind_dir.setText(response.body().getCurrent().getWind_dir() + " Wind");
                    wind_speed.setText((response.body().getCurrent().getWind_kph()) + " Km/h");
                    uv_detail.setText(uv_des);
                    visibility_detail.setText((response.body().getCurrent().getVis_km()) + " Km");
                    air_pressure.setText((response.body().getCurrent().getPressure_mb()) + " hPa");
                    precip_detail.setText((response.body().getCurrent().getPrecip_mm()) + " mm");
                    cloud_cover_detail.setText((response.body().getCurrent().getCloud() + "%"));

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
}


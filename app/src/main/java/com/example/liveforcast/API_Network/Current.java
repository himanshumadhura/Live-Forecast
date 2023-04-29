package com.example.liveforcast.API_Network;

import java.util.List;

public class Current {
    private int last_updated_epoch;
    private String last_updated;
    private int temp_c;
    private double temp_f;
    private int is_day;
    private Condition condition;
    private double wind_mph;
    private double wind_kph;
    private int wind_degree;
    private String wind_dir;
    private double pressure_mb;
    private double pressure_in;
    private double precip_mm;
    private double precip_in;
    private int humidity;
    private int cloud;
    private double feelslike_c;
    private double feelslike_f;
    private double vis_km;
    private double vis_miles;
    private double uv;
    private double gust_mph;
    private double gust_kph;

    public Current() {
    }

    public Current(int last_updated_epoch, String last_updated, int temp_c, double temp_f, int is_day, Condition condition, double wind_mph, double wind_kph, int wind_degree, String wind_dir, double pressure_mb, double pressure_in, double precip_mm, double precip_in, int humidity, int cloud, double feelslike_c, double feelslike_f, double vis_km, double vis_miles, double uv, double gust_mph, double gust_kph) {
        super();
        this.last_updated_epoch = last_updated_epoch;
        this.last_updated = last_updated;
        this.temp_c = temp_c;
        this.temp_f = temp_f;
        this.is_day = is_day;
        this.condition = condition;
        this.wind_mph = wind_mph;
        this.wind_kph = wind_kph;
        this.wind_degree = wind_degree;
        this.wind_dir = wind_dir;
        this.pressure_mb = pressure_mb;
        this.pressure_in = pressure_in;
        this.precip_mm = precip_mm;
        this.precip_in = precip_in;
        this.humidity = humidity;
        this.cloud = cloud;
        this.feelslike_c = feelslike_c;
        this.feelslike_f = feelslike_f;
        this.vis_km = vis_km;
        this.vis_miles = vis_miles;
        this.uv = uv;
        this.gust_mph = gust_mph;
        this.gust_kph = gust_kph;
    }

    public int getLast_updated_epoch() {
        return last_updated_epoch;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public int getTemp_c() {
        return temp_c;
    }

    public double getTemp_f() {
        return temp_f;
    }

    public int getIs_day() {
        return is_day;
    }

    public Condition getCondition() {
        return condition;
    }

    public double getWind_mph() {
        return wind_mph;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public int getWind_degree() {
        return wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public double getPressure_mb() {
        return pressure_mb;
    }

    public double getPressure_in() {
        return pressure_in;
    }

    public double getPrecip_mm() {
        return precip_mm;
    }

    public double getPrecip_in() {
        return precip_in;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public double getFeelslike_c() {
        return feelslike_c;
    }

    public double getFeelslike_f() {
        return feelslike_f;
    }

    public double getVis_km() {
        return vis_km;
    }

    public double getVis_miles() {
        return vis_miles;
    }

    public double getUv() {
        return uv;
    }

    public double getGust_mph() {
        return gust_mph;
    }

    public double getGust_kph() {
        return gust_kph;
    }
}

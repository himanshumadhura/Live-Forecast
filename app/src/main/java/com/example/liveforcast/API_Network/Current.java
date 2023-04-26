package com.example.liveforcast.API_Network;

import java.util.List;

public class Current {
    private String observation_time;
    private int temperature;
    private int weather_code;
    private List<String> weather_icons;
    private List<String> weather_descriptions;
    private int wind_speed;
    private int wind_degree;
    private String wind_dir;
    private int pressure;
    private int precip;
    private int humidity;
    private int cloudcover;
    private int feelslike;
    private int uv_index;
    private int visibility;
    private String is_day;

    public Current() {}

    public Current(String observation_time, int temperature, int weather_code, List<String> weather_icons, List<String> weather_descriptions, int wind_speed, int wind_degree, String wind_dir, int pressure, int precip, int humidity, int cloudcover, int feelslike, int uv_index, int visibility, String is_day) {
        super();
        this.observation_time = observation_time;
        this.temperature = temperature;
        this.weather_code = weather_code;
        this.weather_icons = weather_icons;
        this.weather_descriptions = weather_descriptions;
        this.wind_speed = wind_speed;
        this.wind_degree = wind_degree;
        this.wind_dir = wind_dir;
        this.pressure = pressure;
        this.precip = precip;
        this.humidity = humidity;
        this.cloudcover = cloudcover;
        this.feelslike = feelslike;
        this.uv_index = uv_index;
        this.visibility = visibility;
        this.is_day = is_day;
    }

    public String getObservation_time() {return observation_time;}

    public int getTemperature() {return temperature;}

    public int getWeather_code() {return weather_code;}

    public List<String> getWeather_icons() {return weather_icons;}

    public List<String> getWeather_descriptions() {return weather_descriptions;}

    public int getWind_speed() {return wind_speed;}

    public int getWind_degree() {return wind_degree;}

    public String getWind_dir() {return wind_dir;}

    public int getPressure() {return pressure;}

    public int getPrecip() {return precip;}

    public int getHumidity() {return humidity;}

    public int getCloudcover() {return cloudcover;}

    public int getFeelslike() {return feelslike;}

    public int getUv_index() {return uv_index;}

    public int getVisibility() {return visibility;}

    public String getIs_day() {return is_day;}
}

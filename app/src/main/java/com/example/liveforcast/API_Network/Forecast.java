package com.example.liveforcast.API_Network;

import java.util.List;

public class Forecast {
    private List<Forecastday> forecastday;

    public Forecast() {
    }

    public Forecast(List<Forecastday> forecastday) {
        super();
        this.forecastday = forecastday;
    }

    public List<Forecastday> getForecastday() {
        return forecastday;
    }
}

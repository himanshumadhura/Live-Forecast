package com.example.liveforcast.API_Network;

public class Astro {

    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;

    public Astro() {
    }

    public Astro(String sunrise, String sunset, String moonrise, String moonset) {
        super();
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonset() {
        return moonset;
    }
}

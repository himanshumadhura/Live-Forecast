package com.example.liveforcast.API_Network;

import java.util.List;

public class Forecastday {

    private String date;
    private int date_epoch;
    private Day day;
    private Astro astro;
    private List<Hour> hour;

    public Forecastday() {
    }

    public Forecastday(String date, int date_epoch, Day day, Astro astro, List<Hour> hour) {
        super();
        this.date = date;
        this.date_epoch = date_epoch;
        this.day = day;
        this.astro = astro;
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public int getDate_epoch() {
        return date_epoch;
    }

    public Day getDay() {
        return day;
    }

    public Astro getAstro() {
        return astro;
    }

    public List<Hour> getHour() {
        return hour;
    }
}

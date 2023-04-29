package com.example.liveforcast.API_Network;

public class Location {
    private String name;
    private String country;
    private String region;
    private double lat;
    private double lon;
    private String tz_id;
    private String localtime;
    private int localtime_epoch;

    public Location(){
    }

    public Location(String name, String country, String region, double lat, double lon, String tz_id, String localtime, int localtime_epoch) {
        this.name = name;
        this.country = country;
        this.region = region;
        this.lat = lat;
        this.lon = lon;
        this.tz_id = tz_id;
        this.localtime = localtime;
        this.localtime_epoch = localtime_epoch;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTz_id() {
        return tz_id;
    }

    public String getLocaltime() {
        return localtime;
    }

    public int getLocaltime_epoch() {
        return localtime_epoch;
    }
}

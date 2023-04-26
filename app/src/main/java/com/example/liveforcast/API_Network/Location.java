package com.example.liveforcast.API_Network;

public class Location {
    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private String timezoneId;
    private String localtime;
    private int localtimeEpoch;
    private String utcOffset;

    public Location(){}

    public Location(String name, String country, String region, String lat, String lon, String timezoneId, String localtime, int localtimeEpoch, String utcOffset) {
        super();
        this.name = name;
        this.country = country;
        this.region = region;
        this.lat = lat;
        this.lon = lon;
        this.timezoneId = timezoneId;
        this.localtime = localtime;
        this.localtimeEpoch = localtimeEpoch;
        this.utcOffset = utcOffset;
    }

    public String getName() {return name;}

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public String getLocaltime() {
        return localtime;
    }

    public int getLocaltimeEpoch() {
        return localtimeEpoch;
    }

    public String getUtcOffset() {
        return utcOffset;
    }
}

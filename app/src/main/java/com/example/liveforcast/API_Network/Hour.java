package com.example.liveforcast.API_Network;

public class Hour {

    private String time;
    private double temp_c;
    private double temp_f;
    private Condition_hour condition_hour;

    public Hour() {
    }

    public Hour(String time, double temp_c, double temp_f, Condition_hour condition_hour) {
        super();
        this.time = time;
        this.temp_c = temp_c;
        this.temp_f = temp_f;
        this.condition_hour = condition_hour;
    }

    public String getTime() {
        return time;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public double getTemp_f() {
        return temp_f;
    }

    public Condition_hour getCondition_hour() {
        return condition_hour;
    }
}

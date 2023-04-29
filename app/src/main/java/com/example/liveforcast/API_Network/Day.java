package com.example.liveforcast.API_Network;

public class Day {

    private double maxtemp_c;
    private double maxtemp_f;
    private double mintemp_c;
    private double mintemp_f;

    public Day() {
    }

    public Day(double maxtemp_c, double maxtemp_f, double mintemp_c, double mintemp_f) {
        super();
        this.maxtemp_c = maxtemp_c;
        this.maxtemp_f = maxtemp_f;
        this.mintemp_c = mintemp_c;
        this.mintemp_f = mintemp_f;
    }

    public double getMaxtemp_c() {
        return maxtemp_c;
    }

    public double getMaxtemp_f() {
        return maxtemp_f;
    }

    public double getMintemp_c() {
        return mintemp_c;
    }

    public double getMintemp_f() {
        return mintemp_f;
    }
}

package com.example.liveforcast.API_Network;

public class Condition_hour {

    private String text;
    private String icon;
    private int code;

    public Condition_hour() {
    }

    public Condition_hour(String icon, String text, int code) {
        super();
        this.text = text;
        this.icon = icon;
        this.code = code;
    }

    public String getText(){
        return text;
    }

    public String getIcon() {
        return icon;
    }

    public  int getCode(){
        return code;
    }
}

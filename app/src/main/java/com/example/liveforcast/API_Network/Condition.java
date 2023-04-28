package com.example.liveforcast.API_Network;

public class Condition {
    private String text;
    private String icon;
    private int code;

    public Condition() {
    }

    public Condition(String text, String icon, int code) {
        this.text = text;
        this.icon = icon;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }

    public int getCode() {
        return code;
    }
}

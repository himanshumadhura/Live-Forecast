package com.example.liveforcast.API_Network;

public class Request {

    private String type;
    private String query;

    public Request(){}

    public Request(String type, String query) {
        super();
        this.type = type;
        this.query = query;
    }

    public String getType() {return type;
    }

    public String getQuery() {
        return query;
    }
}

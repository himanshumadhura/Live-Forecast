 package com.example.liveforcast.API_Network;

 public class ModelClass {
      private Location location;
      private Current current;
      private Forecast forecast;

      public ModelClass(){}

      public ModelClass(Location location, Current current, Forecast forecast) {
         super();
         this.location = location;
         this.current = current;
         this.forecast = forecast;
     }

     public Location getLocation() {return location;}
     public Current getCurrent() {return current;}
     public Forecast getForecast() {return forecast;}
 }








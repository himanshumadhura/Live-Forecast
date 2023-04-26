 package com.example.liveforcast.API_Network;

 public class ModelClass {

      private Request request;
      private Location location;
      private Current current;

      public ModelClass(){}

      public ModelClass(Request request, Location location, Current current) {
         super();
         this.request = request;
         this.location = location;
         this.current = current;
     }

     public Request getRequest() {
         return request;
     }
     public Location getLocation() {return location;}
     public Current getCurrent() {return current;}
 }








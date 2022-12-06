package com.example.swaplanguageapp.Models;

public class Model {
    String event;
    String eventusers;
    String series;

    public Model(String event, String eventusers,String series) {
        this.event = event;
        this.eventusers = eventusers;
        this.series = series;
    }

        public String getEvent() {
        return event;
    }

    public void setEvent(String event){
        this.event = event;
   }

    public String getSeries() {

        return series;
    }

    public void setSeries(String series){

        this.series = series;
    }

    public String getEventUsers() {

        return eventusers;
    }

    public void setEventUsers(String eventusers){
        this.eventusers = eventusers;
    }
}
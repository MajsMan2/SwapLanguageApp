package com.example.swaplanguageapp.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarEvent {

    public static ArrayList<CalendarEvent> eventsList = new ArrayList<>();

    public static ArrayList<CalendarEvent> eventsForDate(LocalDate date){

        ArrayList<CalendarEvent> calEvents = new ArrayList<>();

        for(CalendarEvent calEvent : eventsList){
            if(calEvent.getDate().equals(date))
                calEvents.add(calEvent);
        }

        return calEvents;
    }

    private String name;
    private LocalDate date;
    private LocalTime time;

    public CalendarEvent(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
package com.example.swaplanguageapp.Interfaces;

import com.example.swaplanguageapp.Models.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventService {
    @GET("Event/{id}")
    Call<List<Event>> getById(@Path("id") Event event);

    @POST()
    Call<Event> Create(@Body Event event);

    @DELETE("EventUser/{id}")
    Call<Event> DeleteById(@Path("id") Event event);
}
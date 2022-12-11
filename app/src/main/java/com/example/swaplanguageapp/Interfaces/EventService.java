package com.example.swaplanguageapp.Interfaces;

import com.example.swaplanguageapp.Models.CalEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventService {
    @GET("Event/{id}")
    Call<List<CalEvent>> getById(@Path("id") CalEvent event);

    @POST()
    Call<CalEvent> Create(@Body CalEvent event);

    @DELETE("users/{id}")
    Call<CalEvent> DeleteById(@Path("id") CalEvent event);
}

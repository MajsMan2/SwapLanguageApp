package com.example.swaplanguageapp.Interfaces;
import com.example.swaplanguageapp.Models.EventUser;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventUserService {

    @GET("Event/{id}")
    Call<List<EventUser>> getById(@Path("id") EventUser eventUser);

    @POST()
    Call<EventUser> Create(@Body EventUser eventUser, UUID userId);

    @DELETE("users/{id}")
    Call<EventUser> DeleteById(@Path("id") EventUser eventUser);
}
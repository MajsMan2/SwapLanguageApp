package com.example.swaplanguageapp.Interfaces;

import com.example.swaplanguageapp.Models.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeriesService {
    @GET("SeriesEvent/{id}")
    Call<List<Series>> getById(@Path("id") Series series);

    @POST()
    Call<Series> Create(@Body Series series);

    @DELETE("users/{id}")
    Call<Series> DeleteById(@Path("id") Series series);
}

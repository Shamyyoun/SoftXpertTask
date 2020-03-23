package com.example.softxpert.data.datasources.remote;

import com.example.softxpert.data.models.responses.CarsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarApi {
    @GET("cars")
    Single<CarsResponse> getCars(@Query("page") int page);
}

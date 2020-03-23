package com.example.softxpert.data.datasources.remote;

import com.example.softxpert.data.models.User;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/users/{id}")
    Single<User> getUser(@Path("id") int id);
}

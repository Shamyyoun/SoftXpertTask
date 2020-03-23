package com.example.softxpert.data.repositories;

import com.example.softxpert.data.datasources.remote.UserApi;
import com.example.softxpert.data.models.User;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {
    private UserApi userApi;

    @Inject
    public UserRepository(UserApi userApi) {
        this.userApi = userApi;
    }

    public Single<User> modelSingle(int id) {
        return userApi.getUser(id);
    }
}

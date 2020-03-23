package com.example.softxpert.data.repositories;

import com.example.softxpert.data.datasources.remote.CarApi;
import com.example.softxpert.data.models.responses.CarsResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class CarRepository {
    private CarApi carApi;

    @Inject
    public CarRepository(CarApi carApi) {
        this.carApi = carApi;
    }

    public Single<CarsResponse> getCars(int page) {
        return carApi.getCars(page);
    }
}

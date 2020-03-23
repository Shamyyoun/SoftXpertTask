package com.example.softxpert.data.models.responses;

import com.example.softxpert.data.models.entities.Car;

import java.util.List;

public class CarsResponse {
    private int status;
    private List<Car> data;
    private ErrorMessage error;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Car> getData() {
        return data;
    }

    public void setData(List<Car> data) {
        this.data = data;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public boolean isSuccessful() {
        return status == 1;
    }
}

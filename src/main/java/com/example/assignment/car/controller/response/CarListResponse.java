package com.example.assignment.car.controller.response;

import com.example.assignment.car.entity.CarEntity;

import java.util.List;

public class CarListResponse {

    private List<CarEntity> cars;

    public CarListResponse() {
    }

    public CarListResponse(List<CarEntity> cars) {
        this.cars = cars;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }
}

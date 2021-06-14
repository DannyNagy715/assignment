package com.example.assignment.manufacturer.controller.response;

import com.example.assignment.manufacturer.entity.ManufacturerEntity;

import java.util.List;

public class ManufacturerListResponse {

    private List<ManufacturerEntity> manufacturers;

    public ManufacturerListResponse() {
    }

    public List<ManufacturerEntity> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<ManufacturerEntity> manufacturers) {
        this.manufacturers = manufacturers;
    }
}

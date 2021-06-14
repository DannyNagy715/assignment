package com.example.assignment.manufacturer.entity;

import com.example.assignment.car.entity.CarEntity;
import com.example.assignment.core.entity.CoreEntity;

import javax.persistence.*;

@Entity
@Table(name = "manufacturer")
public class ManufacturerEntity extends CoreEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand")
    private CarEntity car;

    public ManufacturerEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}

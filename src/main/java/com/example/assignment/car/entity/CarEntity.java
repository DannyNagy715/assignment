package com.example.assignment.car.entity;

import com.example.assignment.core.entity.CoreEntity;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class CarEntity extends CoreEntity {

    @Column(name = "brand")
    private String brand;

    @Column(name = "type")
    private String type;

    @Column(name = "number_of_doors")
    private Integer doors;

    @Column(name = "prod_year")
    private Integer prodYear;

    public CarEntity() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getProdYear() {
        return prodYear;
    }

    public void setProdYear(Integer prodYear) {
        this.prodYear = prodYear;
    }
}

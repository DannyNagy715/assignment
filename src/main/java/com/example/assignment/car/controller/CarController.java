package com.example.assignment.car.controller;

import com.example.assignment.car.controller.response.CarListResponse;
import com.example.assignment.car.entity.CarEntity;
import com.example.assignment.car.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired
    private CarServiceImpl service;

    @GetMapping(value = "/api/car/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        CarEntity entity = service.findById(id);
        if (entity != null) {
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/api/car")
    public ResponseEntity<CarListResponse> findAll() {
        CarListResponse response = new CarListResponse();
        response.setCars(service.findAll());
        return ResponseEntity.ok(response);
    }

    //create - egy könyv létrehozása
    @PostMapping(value = "/api/car", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarEntity> create(@RequestBody CarEntity entity) {
        service.create(entity);
        return ResponseEntity.ok(entity);
    }

    @PutMapping(value = "/api/car", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarEntity> update(@RequestBody CarEntity entity) {
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping(value = "/api/car/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ResponseEntity.ok("Sikeres művelet");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

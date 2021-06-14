package com.example.assignment.car.service.impl;

import com.example.assignment.car.entity.CarEntity;
import com.example.assignment.car.service.CarService;
import com.example.assignment.core.impl.CoreCRUDServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends CoreCRUDServiceImpl<CarEntity> implements CarService {

    @Override
    protected void updateCore(CarEntity updatableEntity, CarEntity entity) {
        updatableEntity.setBrand(entity.getBrand());
        updatableEntity.setType(entity.getType());
        updatableEntity.setDoors(entity.getDoors());
        updatableEntity.setProdYear(entity.getProdYear());
    }

    @Override
    protected Class<CarEntity> getManagedClass() {
        return CarEntity.class;
    }
}

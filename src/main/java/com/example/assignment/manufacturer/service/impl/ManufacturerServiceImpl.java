package com.example.assignment.manufacturer.service.impl;

import com.example.assignment.core.impl.CoreCRUDServiceImpl;
import com.example.assignment.manufacturer.entity.ManufacturerEntity;
import com.example.assignment.manufacturer.service.ManufacturerService;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl extends CoreCRUDServiceImpl<ManufacturerEntity> implements ManufacturerService {

    @Override
    protected void updateCore(ManufacturerEntity updatableEntity, ManufacturerEntity entity) {
        updatableEntity.setCar(entity.getCar());
        updatableEntity.setName(entity.getName());
    }

    @Override
    protected Class<ManufacturerEntity> getManagedClass() {
        return ManufacturerEntity.class;
    }
}

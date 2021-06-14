package com.example.assignment.user.service.impl;

import com.example.assignment.core.impl.CoreCRUDServiceImpl;
import com.example.assignment.user.entity.AppRoleEntity;
import com.example.assignment.user.service.AppRoleService;

public class AppRoleServiceImpl extends CoreCRUDServiceImpl<AppRoleEntity> implements AppRoleService {

    @Override
    protected void updateCore(AppRoleEntity updatableEntity, AppRoleEntity entity) {
        updatableEntity.setAuthority(entity.getAuthority());
    }

    @Override
    protected Class<AppRoleEntity> getManagedClass() {
        return AppRoleEntity.class;
    }
}

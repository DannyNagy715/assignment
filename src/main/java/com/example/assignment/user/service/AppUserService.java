package com.example.assignment.user.service;

import com.example.assignment.core.CoreCRUDService;
import com.example.assignment.user.entity.AppUserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends CoreCRUDService<AppUserEntity>, UserDetailsService {
}

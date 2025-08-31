package com.inclusive.authservice.service;

import com.inclusive.authservice.model.Permission;

import java.util.List;

public interface PermissionService {
    Permission create(Permission permission);
    Permission update(Long id, Permission permission);
    void delete(Long id);
    Permission getById(Long id);
    List<Permission> getAll();
}



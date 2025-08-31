package com.inclusive.authservice.service.impl;

import com.inclusive.authservice.exception.ResourceNotFoundException;
import com.inclusive.authservice.model.Permission;
import com.inclusive.authservice.repository.PermissionRepository;
import com.inclusive.authservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Long id, Permission updated) {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        existing.setName(updated.getName());
        return permissionRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Permission not found with id " + id);
        }
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission getById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
    }

    @Override
    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }
}



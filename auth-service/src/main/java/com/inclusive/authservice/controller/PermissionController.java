package com.inclusive.authservice.controller;

import com.inclusive.authservice.model.Permission;
import com.inclusive.authservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Permission> create(@RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.create(permission));
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAll() {
        return ResponseEntity.ok(permissionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> update(@PathVariable Long id, @RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.update(id, permission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



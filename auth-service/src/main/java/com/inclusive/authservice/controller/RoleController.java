package com.inclusive.authservice.controller;

import com.inclusive.authservice.dto.RoleRequestDTO;
import com.inclusive.authservice.dto.RoleResponseDTO;
import com.inclusive.authservice.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDTO> create(@Valid @RequestBody RoleRequestDTO dto) {
        return ResponseEntity.ok(roleService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RoleRequestDTO dto) {
        return ResponseEntity.ok(roleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }
}



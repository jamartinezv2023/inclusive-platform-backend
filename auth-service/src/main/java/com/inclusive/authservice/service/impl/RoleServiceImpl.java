package com.inclusive.authservice.service.impl;

import com.inclusive.authservice.dto.RoleRequestDTO;
import com.inclusive.authservice.dto.RoleResponseDTO;
import com.inclusive.authservice.exception.ResourceNotFoundException;
import com.inclusive.authservice.model.Permission;
import com.inclusive.authservice.model.Role;
import com.inclusive.authservice.repository.PermissionRepository;
import com.inclusive.authservice.repository.RoleRepository;
import com.inclusive.authservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public RoleResponseDTO create(RoleRequestDTO dto) {
        Set<Permission> permissions = fetchPermissions(dto.getPermissionIds());

        Role role = Role.builder()
                .name(dto.getName())
                .permissions(permissions)
                .build();

        return toDto(roleRepository.save(role));
    }

    @Override
    public RoleResponseDTO update(Long id, RoleRequestDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));

        role.setName(dto.getName());
        role.setPermissions(fetchPermissions(dto.getPermissionIds()));

        return toDto(roleRepository.save(role));
    }

    @Override
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found with id " + id);
        }
        roleRepository.deleteById(id);
    }

    @Override
    public RoleResponseDTO getById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        return toDto(role);
    }

    @Override
    public List<RoleResponseDTO> getAll() {
        return roleRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private Set<Permission> fetchPermissions(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) return Set.of();
        return ids.stream()
                .map(id -> permissionRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id)))
                .collect(Collectors.toSet());
    }

    private RoleResponseDTO toDto(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setPermissions(
                role.getPermissions().stream()
                        .map(Permission::getName)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}

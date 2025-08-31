package com.inclusive.authservice.service;

import com.inclusive.authservice.dto.RoleRequestDTO;
import com.inclusive.authservice.dto.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    RoleResponseDTO create(RoleRequestDTO dto);
    RoleResponseDTO update(Long id, RoleRequestDTO dto);
    void delete(Long id);
    RoleResponseDTO getById(Long id);
    List<RoleResponseDTO> getAll();
}




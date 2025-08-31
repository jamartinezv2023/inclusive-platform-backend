package com.inclusive.authservice.service;

import com.inclusive.authservice.dto.UserRequestDTO;
import com.inclusive.authservice.dto.UserResponseDTO;
import com.inclusive.authservice.dto.RegisterRequest;
import com.inclusive.authservice.dto.UserDTO;



import java.util.List;

public interface UserService {
    UserResponseDTO create(UserRequestDTO dto);
    UserResponseDTO update(Long id, UserRequestDTO dto);
    void delete(Long id);
    UserResponseDTO getById(Long id);
    List<UserResponseDTO> getAll();
    UserDTO registerUser(RegisterRequest request);
}











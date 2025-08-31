package com.inclusive.authservice.service.impl;

import com.inclusive.authservice.dto.RegisterRequest;
import com.inclusive.authservice.dto.UserDTO;
import com.inclusive.authservice.dto.UserRequestDTO;
import com.inclusive.authservice.dto.UserResponseDTO;
import com.inclusive.authservice.exception.ResourceNotFoundException;
import com.inclusive.authservice.model.Role;
import com.inclusive.authservice.model.User;
import com.inclusive.authservice.repository.RoleRepository;
import com.inclusive.authservice.repository.UserRepository;
import com.inclusive.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        Set<Role> roles = fetchRoles(dto.getRoleIds());

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .emailVerified(false)
                .build();

        return toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        user.setRoles(fetchRoles(dto.getRoleIds()));

        return toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return toUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO registerUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailVerified(false)
                .roles(Set.of()) // Puedes asignar un rol por defecto si deseas
                .build();

        return toUserDTO(userRepository.save(user));
    }

    // --- Helpers ---

    private Set<Role> fetchRoles(Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) return Set.of();
        return roleIds.stream()
                .map(id -> roleRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id)))
                .collect(Collectors.toSet());
    }

    private UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setEmailVerified(user.isEmailVerified());
        dto.setAccountStatus(user.getAccountStatus());

        dto.setRoles(user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet()));

        dto.setPermissions(
                user.getRoles().stream()
                        .flatMap(role -> role.getPermissions().stream())
                        .map(p -> p.getName())
                        .collect(Collectors.toSet())
        );

        return dto;
    }

    private UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setEmailVerified(user.isEmailVerified());
        dto.setAccountStatus(user.getAccountStatus());
        dto.setCreatedAt(user.getCreatedAt());

        dto.setRoles(user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet()));

        dto.setPermissions(user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(p -> p.getName())
                .collect(Collectors.toSet()));

        return dto;
    }
}













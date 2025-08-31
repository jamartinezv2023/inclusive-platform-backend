package com.inclusive.authservice.dto;

import com.inclusive.authservice.model.enums.AccountStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private boolean emailVerified;
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    private Set<String> roles;
    private Set<String> permissions;
}







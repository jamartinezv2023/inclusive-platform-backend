// src/main/java/com/inclusive/authservice/dto/LoginRequest.java
package com.inclusive.authservice.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}



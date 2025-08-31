// src/main/java/com/inclusive/authservice/service/AuthService.java
package com.inclusive.authservice.service;

import com.inclusive.authservice.dto.LoginRequest;
import com.inclusive.authservice.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequest request);
    String register(LoginRequest request); // opcional, si ya tienes método de registro
}


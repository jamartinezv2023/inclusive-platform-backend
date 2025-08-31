// ✅ src/main/java/com/inclusive/authservice/service/impl/AuthServiceImpl.java
package com.inclusive.authservice.service.impl;

import com.inclusive.authservice.dto.LoginRequest;
import com.inclusive.authservice.dto.TokenResponse;
import com.inclusive.authservice.model.User;
import com.inclusive.authservice.model.enums.AccountStatus;
import com.inclusive.authservice.repository.UserRepository;
import com.inclusive.authservice.security.JwtService;
import com.inclusive.authservice.security.UserDetailsImpl; // <-- 🔥 IMPORT NECESARIO
import com.inclusive.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // ✅ Convertir User a UserDetailsImpl antes de pasar a JwtService
        String token = jwtService.generateToken(new UserDetailsImpl(user));
        return new TokenResponse(token);
    }

    @Override
    public String register(LoginRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario ya está registrado");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAccountStatus(AccountStatus.ACTIVE); // ✅ usamos AccountStatus, no setEnabled
        user.setEmailVerified(true); // ✅ nuevo campo en User

        userRepository.save(user);

        return "Usuario registrado correctamente";
    }

}




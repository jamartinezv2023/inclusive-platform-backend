// 📂 src/main/java/com/inclusive/authservice/controller/AuthController.java
package com.inclusive.authservice.controller;

import com.inclusive.authservice.dto.RegisterRequest;
import com.inclusive.authservice.dto.UserDTO;
import com.inclusive.authservice.model.User;
import com.inclusive.authservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 📝 Registro de usuario
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

}







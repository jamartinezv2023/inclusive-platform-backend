// src/main/java/com/inclusive/authservice/controller/ProfileController.java
package com.inclusive.authservice.controller;

import com.inclusive.authservice.dto.*;
import com.inclusive.authservice.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@RequestBody CreateProfileRequest request) {
        return ResponseEntity.ok(profileService.createProfile(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(profileService.updateProfile(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAll() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }
}


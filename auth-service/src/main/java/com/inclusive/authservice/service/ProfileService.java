// src/main/java/com/inclusive/authservice/service/ProfileService.java
package com.inclusive.authservice.service;

import com.inclusive.authservice.dto.*;

import java.util.List;

public interface ProfileService {
    ProfileDTO createProfile(CreateProfileRequest request);
    ProfileDTO updateProfile(Long id, UpdateProfileRequest request);
    void deleteProfile(Long id);
    ProfileDTO getProfileById(Long id);
    List<ProfileDTO> getAllProfiles();
}

// src/main/java/com/inclusive/authservice/service/impl/ProfileServiceImpl.java
package com.inclusive.authservice.service.impl;

import com.inclusive.authservice.dto.*;
import com.inclusive.authservice.model.Profile;
import com.inclusive.authservice.repository.ProfileRepository;
import com.inclusive.authservice.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    private ProfileDTO mapToDTO(Profile profile) {
        return new ProfileDTO(profile.getId(), profile.getNombre(), profile.getDescripcion());
    }

    @Override
    public ProfileDTO createProfile(CreateProfileRequest request) {
        Profile profile = new Profile(request.getNombre(), request.getDescripcion());
        return mapToDTO(profileRepository.save(profile));
    }

    @Override
    public ProfileDTO updateProfile(Long id, UpdateProfileRequest request) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        profile.setNombre(request.getNombre());
        profile.setDescripcion(request.getDescripcion());
        return mapToDTO(profileRepository.save(profile));
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public ProfileDTO getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        return mapToDTO(profile);
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}


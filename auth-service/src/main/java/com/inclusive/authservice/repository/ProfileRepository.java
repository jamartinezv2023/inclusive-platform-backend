// src/main/java/com/inclusive/authservice/repository/ProfileRepository.java
package com.inclusive.authservice.repository;

import com.inclusive.authservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}







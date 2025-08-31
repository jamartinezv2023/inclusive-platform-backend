package com.inclusive.authservice.dto.student;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentPersonalDataDTO {
    private String fullName;
    private LocalDate birthDate;
    private String gender;
    private String ethnicity;
    private String disabilityStatus;
    private String socioEconomicStatus;
    private String familyStructure;
    private String location;
    private String schoolLevel;
    private String guardianName;
    private String avatarUrl;
}


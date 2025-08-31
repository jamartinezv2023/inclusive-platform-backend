package com.inclusive.authservice.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Embeddable
@Getter
@Setter
public class StudentEnvironment {
    private String deviceAccess;
    private String internetAccess;
    private Integer siblingsInSchool;
    private String preferredStudyTime;
}


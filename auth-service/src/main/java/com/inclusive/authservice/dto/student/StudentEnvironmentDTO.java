package com.inclusive.authservice.dto.student;

import lombok.Data;

@Data
public class StudentEnvironmentDTO {
    private String deviceAccess;
    private String internetAccess;
    private Integer siblingsInSchool;
    private String preferredStudyTime;
}


package com.inclusive.authservice.dto.student;

import lombok.Data;

import java.util.List;

@Data
public class StudentLearningProfileDTO {
    private String learningStyleKolb;
    private String learningStyleFelder;
    private String vocationalProfileKuder;
    private List<String> interests;
    private String preferredContentFormat;
    private Boolean needsAssistiveTechnology;
    private String readingLevel;
    private String numeracyLevel;
    private String languageSpokenAtHome;
}


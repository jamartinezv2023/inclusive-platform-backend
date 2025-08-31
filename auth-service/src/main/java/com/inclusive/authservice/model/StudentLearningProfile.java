package com.inclusive.authservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.List;

import lombok.Setter;

@Embeddable
@Getter
@Setter
public class StudentLearningProfile {
    private String learningStyleKolb;
    private String learningStyleFelder;
    private String vocationalProfileKuder;

    @ElementCollection
    private List<String> interests;

    private String preferredContentFormat;
    private Boolean needsAssistiveTechnology;
    private String readingLevel;
    private String numeracyLevel;
    private String languageSpokenAtHome;
}


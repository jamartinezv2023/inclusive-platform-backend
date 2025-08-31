// src/main/java/com/inclusive/authservice/dto/StudentLearningProfileDTO.java

package com.inclusive.authservice.dto;

import jakarta.validation.constraints.*;
import lombok.Value;
import java.io.Serializable;
import java.util.List;

@Value
public class StudentLearningProfileDTO implements Serializable {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    String learningStyleKolb;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    String learningStyleFelder;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    String vocationalProfileKuder;

    @NotNull
    @Size(min = 1)
    List<@NotBlank String> interests;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    String preferredContentFormat;

    @NotNull
    Boolean needsAssistiveTechnology;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(básico|medio|avanzado)$", message = "Debe ser básico, medio o avanzado")
    String readingLevel;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(básico|medio|avanzado)$", message = "Debe ser básico, medio o avanzado")
    String numeracyLevel;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    String languageSpokenAtHome;
}

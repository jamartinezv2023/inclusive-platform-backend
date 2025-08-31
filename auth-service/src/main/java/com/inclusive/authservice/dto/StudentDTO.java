package com.inclusive.authservice.dto;

import com.inclusive.authservice.model.enums.AccountStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentDTO {

    private Long id;
    private Long userId;

    // Datos personales
    private String fullName;
    private LocalDate birthDate;
    private String gender;
    private String ethnicity;
    private String avatarUrl;

    // Contacto y ubicación
    private String location;
    private String guardianName;

    // Perfil de aprendizaje
    private String learningStyleKolb;
    private String learningStyleFelder;
    private String vocationalProfileKuder;
    private List<String> interests;

    // Historial académico
    private Double averageGrade;
    private Double mathScore;
    private Double readingScore;
    private Double scienceScore;

    // Entorno y soporte
    private String deviceAccess;
    private String internetAccess;

    // Estado
    private AccountStatus accountStatus;

    // Tiempos
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}



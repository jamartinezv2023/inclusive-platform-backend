package com.inclusive.authservice.model;

import com.inclusive.authservice.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Relación con User (obligatoria)
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 🔹 Información personal
    @Embedded
    private StudentPersonalData personalData;

    // 🔹 Perfil de aprendizaje
    @Embedded
    private StudentLearningProfile learningProfile;

    // 🔹 Historial académico
    @Embedded
    private StudentAcademicRecord academicRecord;

    // 🔹 Necesidades de apoyo
    @Embedded
    private StudentSupportNeeds supportNeeds;

    // 🔹 Entorno del estudiante
    @Embedded
    private StudentEnvironment environment;

    // 🔹 Resultados de IA
    @Embedded
    private StudentAIOutput aiOutput;

    // 🔹 Estado independiente
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    // 🔹 Fechas
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}




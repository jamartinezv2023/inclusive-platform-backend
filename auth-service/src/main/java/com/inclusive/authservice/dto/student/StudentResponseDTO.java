package com.inclusive.authservice.dto.student;

import com.inclusive.authservice.model.enums.AccountStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentResponseDTO {

    private Long id;
    private Long userId;
    private String username;
    private String email;

    private StudentPersonalDataDTO personalData;
    private StudentLearningProfileDTO learningProfile;
    private StudentAcademicRecordDTO academicRecord;
    private StudentSupportNeedsDTO supportNeeds;
    private StudentEnvironmentDTO environment;
    private StudentAIOutputDTO aiOutput;

    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

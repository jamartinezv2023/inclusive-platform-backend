package com.inclusive.authservice.dto.student;

import com.inclusive.authservice.model.enums.AccountStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequestDTO {

    @NotNull
    private Long userId;

    @Valid
    private StudentPersonalDataDTO personalData;

    @Valid
    private StudentLearningProfileDTO learningProfile;

    @Valid
    private StudentAcademicRecordDTO academicRecord;

    @Valid
    private StudentSupportNeedsDTO supportNeeds;

    @Valid
    private StudentEnvironmentDTO environment;

    @Valid
    private StudentAIOutputDTO aiOutput;

    private AccountStatus accountStatus;
}


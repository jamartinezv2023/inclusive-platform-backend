package com.inclusive.authservice.dto.student;

import lombok.Data;

@Data
public class StudentAIOutputDTO {
    private String recommendedLearningPath;
    private String adaptiveContentProfile;
    private String emotionalStateTrend;
    private String predictedDropoutRisk;
    private String engagementCluster;
}


package com.inclusive.authservice.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Embeddable
@Getter
@Setter
public class StudentAIOutput {
    private String recommendedLearningPath;
    private String adaptiveContentProfile;
    private String emotionalStateTrend;
    private String predictedDropoutRisk;
    private String engagementCluster;
}


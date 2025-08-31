package com.inclusive.authservice.dto.student;

import lombok.Data;

@Data
public class StudentAcademicRecordDTO {
    private Double averageGrade;
    private Double mathScore;
    private Double readingScore;
    private Double scienceScore;
    private Boolean isRepeatingGrade;
    private Double attendanceRate;
    private Double homeworkCompletionRate;
    private String behavioralNotes;
    private Integer disciplinaryActions;
}

package com.inclusive.authservice.service.impl;

import com.inclusive.authservice.dto.student.*;
import com.inclusive.authservice.exception.ResourceNotFoundException;
import com.inclusive.authservice.model.*;
import com.inclusive.authservice.repository.StudentRepository;
import com.inclusive.authservice.repository.UserRepository;
import com.inclusive.authservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inclusive.authservice.model.StudentPersonalData;
import com.inclusive.authservice.model.StudentAcademicRecord;
import com.inclusive.authservice.model.StudentLearningProfile;
import com.inclusive.authservice.model.StudentSupportNeeds;
import com.inclusive.authservice.model.StudentEnvironment;
import com.inclusive.authservice.model.StudentAIOutput;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public StudentResponseDTO create(StudentRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        Student student = Student.builder()
                .user(user)
                .personalData(map(dto.getPersonalData()))
                .learningProfile(map(dto.getLearningProfile()))
                .academicRecord(map(dto.getAcademicRecord()))
                .supportNeeds(map(dto.getSupportNeeds()))
                .environment(map(dto.getEnvironment()))
                .aiOutput(map(dto.getAiOutput()))
                .accountStatus(dto.getAccountStatus())
                .build();

        return toDto(studentRepository.save(student));
    }

    @Override
    public StudentResponseDTO update(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        student.setPersonalData(map(dto.getPersonalData()));
        student.setLearningProfile(map(dto.getLearningProfile()));
        student.setAcademicRecord(map(dto.getAcademicRecord()));
        student.setSupportNeeds(map(dto.getSupportNeeds()));
        student.setEnvironment(map(dto.getEnvironment()));
        student.setAiOutput(map(dto.getAiOutput()));
        student.setAccountStatus(dto.getAccountStatus());

        return toDto(studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDTO getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
        return toDto(student);
    }

    @Override
    public List<StudentResponseDTO> getAll() {
        return studentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getByUserId(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for user id " + userId));
        return toDto(student);
    }

    // -------------------------------
    //         Mappers (Entity -> DTO)
    // -------------------------------

    private StudentResponseDTO toDto(Student student) {
        User user = student.getUser();

        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setUserId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAccountStatus(student.getAccountStatus());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());

        dto.setPersonalData(map(student.getPersonalData()));
        dto.setLearningProfile(map(student.getLearningProfile()));
        dto.setAcademicRecord(map(student.getAcademicRecord()));
        dto.setSupportNeeds(map(student.getSupportNeeds()));
        dto.setEnvironment(map(student.getEnvironment()));
        dto.setAiOutput(map(student.getAiOutput()));

        return dto;
    }

    private StudentPersonalDataDTO map(StudentPersonalData entity) {
        if (entity == null) return null;
        StudentPersonalDataDTO dto = new StudentPersonalDataDTO();
        dto.setFullName(entity.getFullName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());
        dto.setEthnicity(entity.getEthnicity());
        dto.setDisabilityStatus(entity.getDisabilityStatus());
        dto.setSocioEconomicStatus(entity.getSocioEconomicStatus());
        dto.setFamilyStructure(entity.getFamilyStructure());
        dto.setLocation(entity.getLocation());
        dto.setSchoolLevel(entity.getSchoolLevel());
        dto.setGuardianName(entity.getGuardianName());
        dto.setAvatarUrl(entity.getAvatarUrl());
        return dto;
    }

    private StudentLearningProfileDTO map(StudentLearningProfile entity) {
        if (entity == null) return null;
        StudentLearningProfileDTO dto = new StudentLearningProfileDTO();
        dto.setLearningStyleKolb(entity.getLearningStyleKolb());
        dto.setLearningStyleFelder(entity.getLearningStyleFelder());
        dto.setVocationalProfileKuder(entity.getVocationalProfileKuder());
        dto.setInterests(entity.getInterests());
        dto.setPreferredContentFormat(entity.getPreferredContentFormat());
        dto.setNeedsAssistiveTechnology(entity.getNeedsAssistiveTechnology());
        dto.setReadingLevel(entity.getReadingLevel());
        dto.setNumeracyLevel(entity.getNumeracyLevel());
        dto.setLanguageSpokenAtHome(entity.getLanguageSpokenAtHome());
        return dto;
    }

    private StudentAcademicRecordDTO map(StudentAcademicRecord entity) {
        if (entity == null) return null;
        StudentAcademicRecordDTO dto = new StudentAcademicRecordDTO();
        dto.setAverageGrade(entity.getAverageGrade());
        dto.setMathScore(entity.getMathScore());
        dto.setReadingScore(entity.getReadingScore());
        dto.setScienceScore(entity.getScienceScore());
        dto.setIsRepeatingGrade(entity.getIsRepeatingGrade());
        dto.setAttendanceRate(entity.getAttendanceRate());
        dto.setHomeworkCompletionRate(entity.getHomeworkCompletionRate());
        dto.setBehavioralNotes(entity.getBehavioralNotes());
        dto.setDisciplinaryActions(entity.getDisciplinaryActions());
        return dto;
    }

    private StudentSupportNeedsDTO map(StudentSupportNeeds entity) {
        if (entity == null) return null;
        StudentSupportNeedsDTO dto = new StudentSupportNeedsDTO();
        dto.setReceivesPsychologicalSupport(entity.getReceivesPsychologicalSupport());
        dto.setReceivesSpecialEducationSupport(entity.getReceivesSpecialEducationSupport());
        return dto;
    }

    private StudentEnvironmentDTO map(StudentEnvironment entity) {
        if (entity == null) return null;
        StudentEnvironmentDTO dto = new StudentEnvironmentDTO();
        dto.setDeviceAccess(entity.getDeviceAccess());
        dto.setInternetAccess(entity.getInternetAccess());
        dto.setSiblingsInSchool(entity.getSiblingsInSchool());
        dto.setPreferredStudyTime(entity.getPreferredStudyTime());
        return dto;
    }

    private StudentAIOutputDTO map(StudentAIOutput entity) {
        if (entity == null) return null;
        StudentAIOutputDTO dto = new StudentAIOutputDTO();
        dto.setRecommendedLearningPath(entity.getRecommendedLearningPath());
        dto.setAdaptiveContentProfile(entity.getAdaptiveContentProfile());
        dto.setEmotionalStateTrend(entity.getEmotionalStateTrend());
        dto.setPredictedDropoutRisk(entity.getPredictedDropoutRisk());
        dto.setEngagementCluster(entity.getEngagementCluster());
        return dto;
    }

    // -------------------------------
    //         Mappers (DTO -> Entity)
    // -------------------------------

    private StudentPersonalData map(StudentPersonalDataDTO dto) {
        if (dto == null) return null;
        StudentPersonalData data = new StudentPersonalData();
        data.setFullName(dto.getFullName());
        data.setBirthDate(dto.getBirthDate());
        data.setGender(dto.getGender());
        data.setEthnicity(dto.getEthnicity());
        data.setDisabilityStatus(dto.getDisabilityStatus());
        data.setSocioEconomicStatus(dto.getSocioEconomicStatus());
        data.setFamilyStructure(dto.getFamilyStructure());
        data.setLocation(dto.getLocation());
        data.setSchoolLevel(dto.getSchoolLevel());
        data.setGuardianName(dto.getGuardianName());
        data.setAvatarUrl(dto.getAvatarUrl());
        return data;
    }

    private StudentLearningProfile map(StudentLearningProfileDTO dto) {
        if (dto == null) return null;
        StudentLearningProfile profile = new StudentLearningProfile();
        profile.setLearningStyleKolb(dto.getLearningStyleKolb());
        profile.setLearningStyleFelder(dto.getLearningStyleFelder());
        profile.setVocationalProfileKuder(dto.getVocationalProfileKuder());
        profile.setInterests(dto.getInterests());
        profile.setPreferredContentFormat(dto.getPreferredContentFormat());
        profile.setNeedsAssistiveTechnology(dto.getNeedsAssistiveTechnology());
        profile.setReadingLevel(dto.getReadingLevel());
        profile.setNumeracyLevel(dto.getNumeracyLevel());
        profile.setLanguageSpokenAtHome(dto.getLanguageSpokenAtHome());
        return profile;
    }

    private StudentAcademicRecord map(StudentAcademicRecordDTO dto) {
        if (dto == null) return null;
        StudentAcademicRecord record = new StudentAcademicRecord();
        record.setAverageGrade(dto.getAverageGrade());
        record.setMathScore(dto.getMathScore());
        record.setReadingScore(dto.getReadingScore());
        record.setScienceScore(dto.getScienceScore());
        record.setIsRepeatingGrade(dto.getIsRepeatingGrade());
        record.setAttendanceRate(dto.getAttendanceRate());
        record.setHomeworkCompletionRate(dto.getHomeworkCompletionRate());
        record.setBehavioralNotes(dto.getBehavioralNotes());
        record.setDisciplinaryActions(dto.getDisciplinaryActions());
        return record;
    }

    private StudentSupportNeeds map(StudentSupportNeedsDTO dto) {
        if (dto == null) return null;
        StudentSupportNeeds needs = new StudentSupportNeeds();
        needs.setReceivesPsychologicalSupport(dto.getReceivesPsychologicalSupport());
        needs.setReceivesSpecialEducationSupport(dto.getReceivesSpecialEducationSupport());
        return needs;
    }

    private StudentEnvironment map(StudentEnvironmentDTO dto) {
        if (dto == null) return null;
        StudentEnvironment env = new StudentEnvironment();
        env.setDeviceAccess(dto.getDeviceAccess());
        env.setInternetAccess(dto.getInternetAccess());
        env.setSiblingsInSchool(dto.getSiblingsInSchool());
        env.setPreferredStudyTime(dto.getPreferredStudyTime());
        return env;
    }

    private StudentAIOutput map(StudentAIOutputDTO dto) {
        if (dto == null) return null;
        StudentAIOutput ai = new StudentAIOutput();
        ai.setRecommendedLearningPath(dto.getRecommendedLearningPath());
        ai.setAdaptiveContentProfile(dto.getAdaptiveContentProfile());
        ai.setEmotionalStateTrend(dto.getEmotionalStateTrend());
        ai.setPredictedDropoutRisk(dto.getPredictedDropoutRisk());
        ai.setEngagementCluster(dto.getEngagementCluster());
        return ai;
    }

}

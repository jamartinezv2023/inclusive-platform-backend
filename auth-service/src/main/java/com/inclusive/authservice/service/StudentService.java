package com.inclusive.authservice.service;

import com.inclusive.authservice.dto.student.StudentRequestDTO;
import com.inclusive.authservice.dto.student.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    StudentResponseDTO create(StudentRequestDTO dto);

    StudentResponseDTO update(Long id, StudentRequestDTO dto);

    void delete(Long id);

    StudentResponseDTO getById(Long id);

    List<StudentResponseDTO> getAll();

    StudentResponseDTO getByUserId(Long userId);
}




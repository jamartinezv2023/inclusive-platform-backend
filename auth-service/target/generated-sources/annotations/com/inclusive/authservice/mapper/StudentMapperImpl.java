package com.inclusive.authservice.mapper;

import com.inclusive.authservice.dto.CreateStudentRequest;
import com.inclusive.authservice.dto.StudentDTO;
import com.inclusive.authservice.dto.UpdateStudentRequest;
import com.inclusive.authservice.model.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T00:51:14-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO toDTO(Student entity) {
        if ( entity == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        return studentDTO;
    }

    @Override
    public Student toEntity(CreateStudentRequest request) {
        if ( request == null ) {
            return null;
        }

        Student student = new Student();

        return student;
    }

    @Override
    public void updateEntityFromDto(UpdateStudentRequest request, Student entity) {
        if ( request == null ) {
            return;
        }
    }
}

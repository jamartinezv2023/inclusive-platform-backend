package com.inclusive.authservice.mapper;

import com.inclusive.authservice.dto.CreateStudentRequest;
import com.inclusive.authservice.dto.StudentDTO;
import com.inclusive.authservice.dto.UpdateStudentRequest;
import com.inclusive.authservice.model.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDTO(Student entity);

    Student toEntity(CreateStudentRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateStudentRequest request, @MappingTarget Student entity);
}



package com.pbl5.autoattendance.mapper;

import com.pbl5.autoattendance.dto.StudentDTO;
import com.pbl5.autoattendance.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    Student toStudent(StudentDTO studentDTO);
    StudentDTO toStudentDTO(Student student);
}

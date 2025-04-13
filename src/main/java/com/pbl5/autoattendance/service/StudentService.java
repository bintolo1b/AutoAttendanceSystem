package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.dto.ApiResponse;
import com.pbl5.autoattendance.dto.StudentDTO;
import com.pbl5.autoattendance.exception.AppException;
import com.pbl5.autoattendance.exception.ErrorCode;
import com.pbl5.autoattendance.mapper.IStudentMapper;
import com.pbl5.autoattendance.model.Student;
import com.pbl5.autoattendance.model.StudentClass;
import com.pbl5.autoattendance.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {
    StudentRepository studentRepository;
    IStudentMapper studentMapper;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentByUsername(String username) {
        return studentRepository.findByUser_Username(username);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public StudentDTO findById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_EXISTED));
        return studentMapper.toStudentDTO(student);
    }

    public StudentDTO getCurrentStudent() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String name = authentication.getName();
        Student currentStudent = getStudentByUsername(name);
        return studentMapper.toStudentDTO(currentStudent);
    }
}

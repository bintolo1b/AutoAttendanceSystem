package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.model.Student;
import com.pbl5.autoattendance.model.StudentClass;
import com.pbl5.autoattendance.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentByUsername(String username) {
        return studentRepository.findByUser_Username(username);
    }
}

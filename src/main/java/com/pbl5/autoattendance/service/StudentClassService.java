package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.model.StudentClass;
import com.pbl5.autoattendance.repository.StudentClassRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentClassService {
    private final StudentClassRepository studentClassRepository;

    public StudentClassService(StudentClassRepository studentClassRepository) {
        this.studentClassRepository = studentClassRepository;
    }


    public void save(StudentClass studentClass) {
        studentClassRepository.save(studentClass);
    }
}

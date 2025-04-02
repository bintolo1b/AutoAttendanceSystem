package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.model.StudentAttendance;
import com.pbl5.autoattendance.repository.StudentAttendanceRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentAttendanceService{

    private final StudentAttendanceRepository studentAttendanceRepository;

    public StudentAttendanceService(StudentAttendanceRepository studentAttendanceRepository) {
        this.studentAttendanceRepository = studentAttendanceRepository;
    }

    public StudentAttendance saveAttendance(StudentAttendance attendance) {
        return studentAttendanceRepository.save(attendance);
    }
}

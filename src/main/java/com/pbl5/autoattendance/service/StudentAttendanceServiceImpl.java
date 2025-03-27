package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.model.StudentAttendance;
import com.pbl5.autoattendance.repository.StudentAttendanceRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService{

    private final StudentAttendanceRepository studentAttendanceRepository;

    public StudentAttendanceServiceImpl(StudentAttendanceRepository studentAttendanceRepository) {
        this.studentAttendanceRepository = studentAttendanceRepository;
    }

    @Override
    public StudentAttendance saveAttendance(StudentAttendance attendance) {
        return studentAttendanceRepository.save(attendance);
    }
}

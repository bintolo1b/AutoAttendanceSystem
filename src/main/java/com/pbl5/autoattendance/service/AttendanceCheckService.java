package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.embedded.AttendanceCheckId;
import com.pbl5.autoattendance.model.AttendanceCheck;
import com.pbl5.autoattendance.repository.AttendanceCheckRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceCheckService {
    private final AttendanceCheckRepository attendanceCheckRepository;

    public AttendanceCheckService(AttendanceCheckRepository attendanceCheckRepository) {
        this.attendanceCheckRepository = attendanceCheckRepository;
    }

    public AttendanceCheck getAttendanceCheckById(AttendanceCheckId id) {
        return attendanceCheckRepository.findById(id).orElse(null);
    }

    public AttendanceCheck saveAttendanceCheck(AttendanceCheck attendanceCheck) {
        return attendanceCheckRepository.save(attendanceCheck);
    }
}

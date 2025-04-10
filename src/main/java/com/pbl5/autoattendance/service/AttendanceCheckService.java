package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.dto.AttendanceCheckDTO;
import com.pbl5.autoattendance.embedded.AttendanceCheckId;
import com.pbl5.autoattendance.model.AttendanceCheck;
import com.pbl5.autoattendance.model.Class;
import com.pbl5.autoattendance.model.Lesson;
import com.pbl5.autoattendance.model.Student;
import com.pbl5.autoattendance.repository.AttendanceCheckRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void createAttendanceCheck(Student student, Class aclass) {
        List<Lesson> lessons = aclass.getLessons();
        for(Lesson lesson : lessons) {
            AttendanceCheckId id = AttendanceCheckId.builder()
                    .lessonId(lesson.getId())
                    .studentId(student.getId())
                    .build();
            AttendanceCheck attendanceCheck = AttendanceCheck.builder()
                    .id(id)
                    .checkinDate(null)
                    .status(null)
                    .imgPath("")
                    .lesson(lesson)
                    .student(student)
                    .build();
            attendanceCheckRepository.save(attendanceCheck);
        }
    }

    public void update(AttendanceCheck attendanceCheck) {
        attendanceCheckRepository.save(attendanceCheck);
    }

    public AttendanceCheck findById(AttendanceCheckId id) {
        return attendanceCheckRepository.findById(id).orElse(null);
    }
}

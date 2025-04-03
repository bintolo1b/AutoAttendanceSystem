package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.repository.StudentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class StudentAttendanceAPI {

//    private final StudentAttendanceService service;
//
//    private final StudentRepository studentRepository;
//
//    private final AttendanceRepository attendanceRepository;
//
//    public StudentAttendanceAPI(StudentAttendanceService service, StudentRepository studentRepository, AttendanceRepository attendanceRepository) {
//        this.service = service;
//        this.studentRepository = studentRepository;
//        this.attendanceRepository = attendanceRepository;
//    }

    //nhan vo student id, lesson id, xong luu vo bang student_lesson
//    @PostMapping("/save")
//    public StudentAttendance save(@RequestBody StudentAttendanceDTO request) {
//
//        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));
//
//        Attendance attendance = attendanceRepository.findById(request.getAttendanceId()).orElseThrow(() -> new RuntimeException("Attendance not found"));
//
//        StudentAttendanceId studentAttendanceId = new StudentAttendanceId(request.getStudentId(), request.getAttendanceId());
//
//        StudentAttendance studentAttendance = new StudentAttendance();
//        studentAttendance.setStudent(student);
//        studentAttendance.setAttendance(attendance);
//        studentAttendance.setId(studentAttendanceId);
//        studentAttendance.setCheckinDate(request.getCheckInDate());
//        studentAttendance.setImgPath(request.getImgPath());
//
//        return service.saveAttendance(studentAttendance);
//    }

}

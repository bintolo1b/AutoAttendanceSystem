package com.pbl5.autoattendance.model;

import com.pbl5.autoattendance.embedded.StudentAttendanceId;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class StudentAttendance {
    @EmbeddedId
    StudentAttendanceId id;

    @Column(nullable = false)
    private LocalDateTime checkinDate;

    @Column(nullable = false)
    private String imgPath;

    @MapsId("studentId")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @MapsId("attendanceId")
    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    public StudentAttendance() {

    }

    public StudentAttendance(StudentAttendanceId id, LocalDateTime checkinDate, String imgPath, Student student, Attendance attendance) {
        this.id = id;
        this.checkinDate = checkinDate;
        this.imgPath = imgPath;
        this.student = student;
        this.attendance = attendance;
    }

    public StudentAttendanceId getId() {
        return id;
    }

    public void setId(StudentAttendanceId id) {
        this.id = id;
    }

    public LocalDateTime getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDateTime checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}

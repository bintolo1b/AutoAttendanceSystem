package com.pbl5.autoattendance.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentAttendanceDTO {
    private int studentId;
    private int attendanceId;
    private LocalDateTime checkInDate;
    private String imgPath;

    public StudentAttendanceDTO() {
    }

    public StudentAttendanceDTO(int studentId, int attendanceId, LocalDateTime checkInDate, String imgPath) {
        this.studentId = studentId;
        this.attendanceId = attendanceId;
        this.checkInDate = checkInDate;
        this.imgPath = imgPath;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}

package com.pbl5.autoattendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class StudentAttendanceDTO {
    private int studentId;
    private int attendanceId;
    private LocalDateTime checkInDate;
    private String imgPath;
}

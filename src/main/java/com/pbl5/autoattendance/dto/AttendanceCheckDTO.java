package com.pbl5.autoattendance.dto;

import com.pbl5.autoattendance.embedded.AttendanceCheckId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceCheckDTO {
    private AttendanceCheckId id;

    private LocalDateTime checkinDate;

    private String imgPath;


    private Integer lessonId;

    private Integer studentId;
}

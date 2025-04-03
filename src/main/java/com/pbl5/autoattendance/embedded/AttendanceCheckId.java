package com.pbl5.autoattendance.embedded;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AttendanceCheckId implements Serializable {
    private Integer studentId;
    private Integer lessonId;
}

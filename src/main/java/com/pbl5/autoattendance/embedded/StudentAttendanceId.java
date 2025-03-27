package com.pbl5.autoattendance.embedded;

import java.io.Serializable;

public class StudentAttendanceId implements Serializable {
    private Integer studentId;
    private Integer attendanceId;

    public StudentAttendanceId() {
    }

    public StudentAttendanceId(Integer studentId, Integer attendanceId) {
        this.studentId = studentId;
        this.attendanceId = attendanceId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }
}

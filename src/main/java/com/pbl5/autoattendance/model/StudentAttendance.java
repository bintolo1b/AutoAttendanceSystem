package com.pbl5.autoattendance.model;

import com.pbl5.autoattendance.embedded.StudentAttendanceId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}

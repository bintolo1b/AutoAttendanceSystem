package com.pbl5.autoattendance.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "attendance")
    private List<StudentAttendance> studentAttendances;
}

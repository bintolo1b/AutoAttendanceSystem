package com.pbl5.autoattendance.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class aClass;

    @Column(nullable = false)
    private java.util.Date lessonDate; // Ngày cụ thể của buổi học

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column
    private String room;

    @Column
    private Boolean isCompleted = false; // Trạng thái buổi học đã hoàn thành hay chưa

    @Column
    private String notes; // Ghi chú cho buổi học

    @OneToMany(mappedBy = "lesson")
    private List<AttendanceCheck> attendanceChecks;

}
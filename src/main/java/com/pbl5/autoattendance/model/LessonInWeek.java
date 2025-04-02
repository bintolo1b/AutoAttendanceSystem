package com.pbl5.autoattendance.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LessonInWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class aClass;
    
    @Column(nullable = false)
    private Integer dayOfWeek; // 1-7 tương ứng với thứ 2 đến chủ nhật
    
    @Column(nullable = false)
    private LocalTime startTime;
    
    @Column(nullable = false)
    private LocalTime endTime;
    
    @Column
    private String room;
    
}

package com.pbl5.autoattendance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;
    
    @Column(name = "number_of_weeks")
    private Integer numberOfWeeks;

    @OneToMany(mappedBy = "aClass")
    private List<StudentClass> studentClasses;

    @ManyToOne
    @JoinColumn(nullable = false, name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "aClass")
    private List<Lesson> lessons;
}

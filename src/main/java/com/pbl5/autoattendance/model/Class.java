package com.pbl5.autoattendance.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "aClass")
    private List<StudentClass> studentClasses;

    @ManyToOne
    @JoinColumn(nullable = false, name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "aClass")
    private List<LessonInWeek> lessonsInWeek;
}

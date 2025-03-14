package com.pbl5.autoattendance.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(length = 10)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @OneToMany(mappedBy = "student")
    private List<StudentAttendance> studentAttendances;

    @OneToMany(mappedBy = "student")
    private List<StudentClass> studentClasses;
}

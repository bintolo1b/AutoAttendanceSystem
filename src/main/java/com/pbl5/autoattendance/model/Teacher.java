package com.pbl5.autoattendance.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false ,unique = true)
    private String email;

    @OneToMany(mappedBy = "teacher")
    private List<Class> classes;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;
}

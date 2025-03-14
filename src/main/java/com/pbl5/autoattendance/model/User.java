package com.pbl5.autoattendance.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 50, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;
}

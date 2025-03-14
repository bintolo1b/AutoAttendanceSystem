package com.pbl5.autoattendance.model;

import jakarta.persistence.*;

@Entity
public class StudentVector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "feature_vector", columnDefinition = "json")
    private String featureVector;
}

package com.pbl5.autoattendance.repository;

import com.pbl5.autoattendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}

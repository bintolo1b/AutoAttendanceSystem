package com.pbl5.autoattendance.repository;

import com.pbl5.autoattendance.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}

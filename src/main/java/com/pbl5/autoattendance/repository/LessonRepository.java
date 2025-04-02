package com.pbl5.autoattendance.repository;

import com.pbl5.autoattendance.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    public List<Lesson> findByaClass_Id(Integer classId);
}

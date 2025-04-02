package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.model.Lesson;
import com.pbl5.autoattendance.repository.LessonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getLessonsByClassId(Integer classId) {
        return lessonRepository.findByaClass_Id(classId);
    }

    public Lesson getLessonById(int id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson with ID " + id + " not found"));
    }
}

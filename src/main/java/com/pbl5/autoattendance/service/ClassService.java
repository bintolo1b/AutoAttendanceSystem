package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.dto.ClassDTO;
import com.pbl5.autoattendance.dto.ClassWithLessonDTO;
import com.pbl5.autoattendance.model.Class;
import com.pbl5.autoattendance.model.Teacher;
import com.pbl5.autoattendance.repository.ClassRepository;
import com.pbl5.autoattendance.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassService {
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    public ClassService(ClassRepository classRepository, TeacherRepository teacherRepository) {
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Class getClassById(int id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class with ID " + id + " not found"));
    }

    public Class getClassByLessonId(int lessonId) {
        return classRepository.findClassByLessonId(lessonId);
    }

    public Class crateNewClass(ClassWithLessonDTO classWithLessonDTO, Teacher teacher) {
        Class newClass = Class.builder()
                .createdAt(LocalDateTime.now())
                .name(classWithLessonDTO.getName())
                .numberOfWeeks(classWithLessonDTO.getNumberOfWeeks())
                .teacher(teacher)
                .build();
        return classRepository.save(newClass);
    }


}

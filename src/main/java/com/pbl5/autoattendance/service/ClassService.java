package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.dto.ClassDTO;
import com.pbl5.autoattendance.dto.ClassWithLessonDTO;
import com.pbl5.autoattendance.model.Class;
import com.pbl5.autoattendance.model.Student;
import com.pbl5.autoattendance.model.StudentClass;
import com.pbl5.autoattendance.model.Teacher;
import com.pbl5.autoattendance.repository.ClassRepository;
import com.pbl5.autoattendance.repository.StudentClassRepository;
import com.pbl5.autoattendance.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassService {
    ClassRepository classRepository;
    TeacherRepository teacherRepository;
    StudentClassRepository studentClassRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Class getClassById(int id) {
        return classRepository.findById(id).orElse(null);
    }

    public Class getClassByLessonId(int lessonId) {
        return classRepository.findClassByLessonId(lessonId);
    }

    public Class createNewClass(ClassWithLessonDTO classWithLessonDTO, Teacher teacher) {
        Class newClass = Class.builder()
                .createdAt(LocalDateTime.now())
                .name(classWithLessonDTO.getName())
                .numberOfWeeks(classWithLessonDTO.getNumberOfWeeks())
                .teacher(teacher)
                .build();
        return classRepository.save(newClass);
    }


    public List<Class> getAllClassesOfTeacher(Teacher teacher) {
        return classRepository.findByTeacher(teacher);
    }

    public List<Class> getAllClasessOfStudent(Student student) {
        return studentClassRepository.findByStudent(student)
                .stream()
                .map(StudentClass::getAClass)
                .collect(Collectors.toList());
    }
}

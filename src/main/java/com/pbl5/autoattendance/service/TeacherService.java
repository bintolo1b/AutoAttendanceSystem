package com.pbl5.autoattendance.service;

import com.pbl5.autoattendance.model.Teacher;
import com.pbl5.autoattendance.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}

package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.TeacherDTO;
import com.pbl5.autoattendance.model.Teacher;
import com.pbl5.autoattendance.service.TeacherService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/teacher")
public class TeacherAPI {
    private final TeacherService teacherService;

    public TeacherAPI(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    }
}

package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.TeacherDTO;
import com.pbl5.autoattendance.model.Teacher;
import com.pbl5.autoattendance.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherAPI {
    private final TeacherService teacherService;

    public TeacherAPI(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<?> getTeacherById(@PathVariable Integer teacherId){
        Teacher teacher = teacherService.getTeacherById(teacherId);
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setPhone(teacher.getPhone());
        return ResponseEntity.ok(teacherDTO);
    }
}

package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.TeacherDTO;
import com.pbl5.autoattendance.model.Teacher;
import com.pbl5.autoattendance.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherAPI {
    private final TeacherService teacherService;


    public TeacherAPI(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<TeacherDTO> getCurrentTeacher(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        TeacherDTO dto = convertToTeacherDTO(teacherService.getTeacherByUsername(username));
        System.out.println("bro");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private TeacherDTO convertToTeacherDTO(Teacher teacher){
        return TeacherDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .phone(teacher.getPhone())
                .email(teacher.getEmail())
                .username(teacher.getUser().getUsername())
                .build();
    }
}

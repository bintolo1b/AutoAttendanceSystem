package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.ApiResponse;
import com.pbl5.autoattendance.dto.StudentDTO;
import com.pbl5.autoattendance.mapper.IStudentMapper;
import com.pbl5.autoattendance.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentAPI {
    StudentService studentService;

    @GetMapping
    public ApiResponse<StudentDTO> getCurrentStudent(){
        StudentDTO result = studentService.getCurrentStudent();
        return ApiResponse.<StudentDTO>builder()
                .code(1000)
                .result(result)
                .build();
    }
}

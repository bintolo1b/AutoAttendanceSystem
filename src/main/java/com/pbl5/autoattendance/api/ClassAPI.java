package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.ClassDTO;
import com.pbl5.autoattendance.dto.ClassWithLessonDTO;
import com.pbl5.autoattendance.dto.StudentDTO;
import com.pbl5.autoattendance.model.*;
import com.pbl5.autoattendance.model.Class;
import com.pbl5.autoattendance.service.ClassService;
import com.pbl5.autoattendance.service.LessonService;
import com.pbl5.autoattendance.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classes")
public class ClassAPI {
    
    private final ClassService classService;
    private final TeacherService teacherService;
    private final LessonService lessonService;
    
    public ClassAPI(ClassService classService, TeacherService teacherService, LessonService lessonService) {
        this.classService = classService;
        this.teacherService = teacherService;
        this.lessonService = lessonService;
    }
    
    @GetMapping
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        List<ClassDTO> classDTOs = classes.stream()
                .map(this::convertToDTO)
                .toList();
        return new ResponseEntity<>(classDTOs, HttpStatus.OK);
    }
    
    @GetMapping("/{classId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByClassId(@PathVariable Integer classId) {
        Class classEntity = classService.getClassById(classId);
        if (classEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        List<StudentDTO> studentDTOs = classEntity.getStudentClasses().stream()
                .map(StudentClass::getStudent)
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }
    
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<ClassDTO> getClassByLessonId(@PathVariable Integer lessonId) {
        Class classEntity = classService.getClassByLessonId(lessonId);
        if (classEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        ClassDTO classDTO = convertToDTO(classEntity);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewClass(@RequestBody @Valid ClassWithLessonDTO classWithLessonDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = teacherService.getTeacherByUsername(username);
        if (teacher == null){
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "Teacher not found");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Class newClass = classService.crateNewClass(classWithLessonDTO, teacher);
        List<Lesson> lessons = lessonService.createLessons(classWithLessonDTO.getSchedule(), newClass, newClass.getNumberOfWeeks());
        return new ResponseEntity<>(convertToDTO(newClass), HttpStatus.CREATED);
    }
    
    private StudentDTO convertToStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setPhoneNumber(student.getPhone());
        dto.setEmail(student.getEmail());
        if (student.getUser() != null) {
            dto.setUsername(student.getUser().getUsername());
        }
        return dto;
    }
    
    private ClassDTO convertToDTO(Class classEntity) {
        ClassDTO dto = new ClassDTO();
        dto.setId(classEntity.getId());
        dto.setCreatedAt(classEntity.getCreatedAt());
        dto.setNumberOfWeeks(classEntity.getNumberOfWeeks());
        dto.setName(classEntity.getName());
        if (classEntity.getTeacher() != null) {
            dto.setTeacherId(classEntity.getTeacher().getId());
        }
        return dto;
    }
}

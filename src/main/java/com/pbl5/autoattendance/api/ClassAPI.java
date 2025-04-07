package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.ClassDTO;
import com.pbl5.autoattendance.dto.StudentDTO;
import com.pbl5.autoattendance.model.Class;
import com.pbl5.autoattendance.model.Student;
import com.pbl5.autoattendance.model.StudentClass;
import com.pbl5.autoattendance.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classes")
public class ClassAPI {
    
    private final ClassService classService;
    
    public ClassAPI(ClassService classService) {
        this.classService = classService;
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
        dto.setName(classEntity.getName());
        if (classEntity.getTeacher() != null) {
            dto.setTeacherId(classEntity.getTeacher().getId());
        }
        return dto;
    }
}

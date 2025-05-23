package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.ClassDTO;
import com.pbl5.autoattendance.dto.ClassWithLessonDTO;
import com.pbl5.autoattendance.dto.LessonTimeRangeDTO;
import com.pbl5.autoattendance.dto.StudentDTO;
import com.pbl5.autoattendance.model.*;
import com.pbl5.autoattendance.model.Class;
import com.pbl5.autoattendance.service.ClassService;
import com.pbl5.autoattendance.service.LessonService;
import com.pbl5.autoattendance.service.StudentService;
import com.pbl5.autoattendance.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    private final StudentService studentService;

    public ClassAPI(ClassService classService, TeacherService teacherService, LessonService lessonService, StudentService studentService) {
        this.classService = classService;
        this.teacherService = teacherService;
        this.lessonService = lessonService;
        this.studentService = studentService;
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

    @GetMapping("/student/my-classes")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getAllClassesOfStudent(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.getStudentByUsername(username);
        List<ClassDTO> classes = classService.getAllClasessOfStudent(student)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/teacher/my-classes")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> getAllClassesOfTeacher(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = teacherService.getTeacherByUsername(username);
        List<ClassDTO> classes = classService.getAllClassesOfTeacher(teacher)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> createNewClass(@RequestBody @Valid ClassWithLessonDTO classWithLessonDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = teacherService.getTeacherByUsername(username);
        if (teacher == null){
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "Teacher not found");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra thời gian bắt đầu và kết thúc của từng buổi học
        try {
            for (LessonTimeRangeDTO lessonTime : classWithLessonDTO.getSchedule().values()) {
                lessonTime.validateTimeRange();
            }
        } catch (IllegalArgumentException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        if (classService.checkScheduleConflict(teacher, classWithLessonDTO)){
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "Schedule conflict");
            return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
        }

        Class newClass = classService.createNewClass(classWithLessonDTO, teacher);
        List<Lesson> lessons = lessonService.createLessons(classWithLessonDTO.getSchedule(), newClass, newClass.getNumberOfWeeks());
        return new ResponseEntity<>(convertToDTO(newClass), HttpStatus.CREATED);
    }

    @GetMapping("/{classId}")
    public ResponseEntity<?> getClassById(@PathVariable Integer classId) {
        Class aclass = classService.getClassById(classId);
        if (aclass == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClassDTO classDTO = convertToDTO(aclass);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @GetMapping("/{classId}/with-schedule")
    public ResponseEntity<?> getClassWithLessonById(@PathVariable Integer classId) {
        Class aclass = classService.getClassById(classId);
        if (aclass == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Get all lessons for this class
        List<Lesson> allLessons = lessonService.getLessonsByClassId(classId);
        System.out.println(allLessons.size());
        if (allLessons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Sort lessons by date
        allLessons.sort((l1, l2) -> l1.getLessonDate().compareTo(l2.getLessonDate()));
        
        // Get the first lesson date
        LocalDate firstLessonDate = allLessons.get(0).getLessonDate();


        
        // Find all lessons in the first week
        List<Lesson> firstWeekLessons = allLessons.stream()
                .filter(lesson -> lesson.getLessonDate().isBefore(firstLessonDate.plusDays(7)))
                .toList();

        System.out.println(firstWeekLessons.size());

        // Create schedule map from first week lessons
        Map<String, LessonTimeRangeDTO> schedule = new HashMap<>();
        for (Lesson lesson : firstWeekLessons) {
            String dayOfWeek = lesson.getLessonDate().getDayOfWeek().toString();
            LessonTimeRangeDTO timeRange = new LessonTimeRangeDTO();
            timeRange.setStartTime(lesson.getStartTime());
            timeRange.setEndTime(lesson.getEndTime());
            schedule.put(dayOfWeek, timeRange);
        }
        
        // Build ClassWithLessonDTO
        ClassWithLessonDTO dto = new ClassWithLessonDTO();
        dto.setName(aclass.getName());
        dto.setNumberOfWeeks(aclass.getNumberOfWeeks());
        dto.setSchedule(schedule);
        
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    private StudentDTO convertToStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setPhone(student.getPhone());
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

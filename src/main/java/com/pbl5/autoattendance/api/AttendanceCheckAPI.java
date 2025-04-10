package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.AttendanceCheckDTO;
import com.pbl5.autoattendance.embedded.AttendanceCheckId;
import com.pbl5.autoattendance.model.AttendanceCheck;
import com.pbl5.autoattendance.model.Lesson;
import com.pbl5.autoattendance.service.AttendanceCheckService;
import com.pbl5.autoattendance.service.ClassService;
import com.pbl5.autoattendance.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceCheckAPI {
    private final AttendanceCheckService attendanceCheckService;
    private final ClassService classService;
    private final LessonService lessonService;

    public AttendanceCheckAPI(AttendanceCheckService attendanceCheckService, ClassService classService, LessonService lessonService) {
        this.attendanceCheckService = attendanceCheckService;
        this.classService = classService;
        this.lessonService = lessonService;
    }

    @PostMapping("/check")
    public ResponseEntity<AttendanceCheckDTO> checkAttendance(@RequestBody Map<String, Integer> request) {
        Integer lessonId = request.get("lessonId");
        Integer studentId = request.get("studentId");
        
        if (lessonId == null || studentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        AttendanceCheckId id = new AttendanceCheckId();
        id.setLessonId(lessonId);
        id.setStudentId(studentId);
        
        AttendanceCheck attendanceCheck = attendanceCheckService.getAttendanceCheckById(id);
        
        if (attendanceCheck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        attendanceCheck.setCheckinDate(LocalDateTime.now());
        Lesson lesson = lessonService.getLessonById(lessonId);

        if (LocalTime.now().isAfter(lesson.getStartTime().plusMinutes(10))) {
            attendanceCheck.setStatus("Late");
        } else {
            attendanceCheck.setStatus("Attended");
        }

        attendanceCheckService.saveAttendanceCheck(attendanceCheck);

        AttendanceCheckDTO dto = convertToDTO(attendanceCheck);
        
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    
    @PostMapping("/get_status")
    public ResponseEntity<AttendanceCheckDTO> getAttendanceCheck(@RequestBody Map<String, Integer> request) {
        Integer lessonId = request.get("lessonId");
        Integer studentId = request.get("studentId");
        if (lessonId == null || studentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        AttendanceCheckId id = new AttendanceCheckId();
        id.setLessonId(lessonId);
        id.setStudentId(studentId);
        
        AttendanceCheck attendanceCheck = attendanceCheckService.getAttendanceCheckById(id);
        
        if (attendanceCheck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        AttendanceCheckDTO dto = convertToDTO(attendanceCheck);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> updateCheckAttendance(@Valid @RequestBody AttendanceCheckDTO attendanceCheckDTO){
        AttendanceCheckId id = AttendanceCheckId.builder()
                .studentId(attendanceCheckDTO.getStudentId())
                .lessonId(attendanceCheckDTO.getLessonId())
                .build();
        AttendanceCheck attendanceCheck = attendanceCheckService.findById(id);
        if (attendanceCheck == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        attendanceCheck.setStatus(attendanceCheckDTO.getStatus());
        attendanceCheck.setImgPath(attendanceCheckDTO.getImgPath());
        attendanceCheck.setCheckinDate(attendanceCheckDTO.getCheckinDate());
        attendanceCheckService.update(attendanceCheck);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //
    
    private AttendanceCheckDTO convertToDTO(AttendanceCheck attendanceCheck) {
        AttendanceCheckDTO dto = new AttendanceCheckDTO();
        dto.setCheckinDate(attendanceCheck.getCheckinDate());
        dto.setImgPath(attendanceCheck.getImgPath());
        dto.setLessonId(attendanceCheck.getLesson().getId());
        dto.setStudentId(attendanceCheck.getStudent().getId());
        dto.setStatus(attendanceCheck.getStatus());
        return dto;
    }
}

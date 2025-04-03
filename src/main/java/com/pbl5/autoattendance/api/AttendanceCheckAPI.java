package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.AttendanceCheckDTO;
import com.pbl5.autoattendance.embedded.AttendanceCheckId;
import com.pbl5.autoattendance.model.AttendanceCheck;
import com.pbl5.autoattendance.service.AttendanceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceCheckAPI {
    
    @Autowired
    private AttendanceCheckService attendanceCheckService;

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

    //
    
    private AttendanceCheckDTO convertToDTO(AttendanceCheck attendanceCheck) {
        AttendanceCheckDTO dto = new AttendanceCheckDTO();
        dto.setId(attendanceCheck.getId());
        dto.setCheckinDate(attendanceCheck.getCheckinDate());
        dto.setImgPath(attendanceCheck.getImgPath());
        dto.setLessonId(attendanceCheck.getLesson().getId());
        dto.setStudentId(attendanceCheck.getStudent().getId());
        return dto;
    }
}

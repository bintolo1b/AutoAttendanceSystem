package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.dto.LessonDTO;
import com.pbl5.autoattendance.model.Lesson;
import com.pbl5.autoattendance.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonAPI {
    
    private final LessonService lessonService;

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<LessonDTO>> getLessonsByClassId(@PathVariable Integer classId) {
        List<Lesson> lessons = lessonService.getLessonsByClassId(classId);
        List<LessonDTO> lessonDTODTOS = lessons.stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(lessonDTODTOS);
    }
    
    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Integer lessonId) {
        Lesson lesson = lessonService.getLessonById(lessonId);
        if (lesson == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LessonDTO lessonDTO = convertToDTO(lesson);
        return ResponseEntity.ok(lessonDTO);
    }
    
    private LessonDTO convertToDTO(Lesson lesson) {
        LessonDTO dto = new LessonDTO();
        dto.setId(lesson.getId());
        dto.setClass_id(lesson.getAClass().getId());
        dto.setLessonDate(lesson.getLessonDate());
        dto.setStartTime(lesson.getStartTime());
        dto.setEndTime(lesson.getEndTime());
        dto.setRoom(lesson.getRoom());
        dto.setIsCompleted(lesson.getIsCompleted());
        dto.setNotes(lesson.getNotes());
        return dto;
    }
}

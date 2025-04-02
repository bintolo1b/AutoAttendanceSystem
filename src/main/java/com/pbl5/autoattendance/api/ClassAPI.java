package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.model.Class;
import com.pbl5.autoattendance.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/classes")
public class ClassAPI {

//    @Autowired
//    private com.pbl5.autoattendance.service.ClassService classService;
//
//    @PostMapping
//    public ResponseEntity<Object> addClass(@RequestBody Class classData) {
//        try {
//            Class savedClass = classService.saveClass(classData);
//            return new ResponseEntity<>(savedClass, HttpStatus.CREATED);
//        } catch (Exception e) {
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "Không thể thêm lớp học: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
}

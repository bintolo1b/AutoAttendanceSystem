package com.pbl5.autoattendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class LessonDTO {
    private Integer id;
    private Integer class_id;
    private java.util.Date lessonDate; // Ngày cụ thể của buổi học
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
    private Boolean isCompleted = false; // Trạng thái buổi học đã hoàn thành hay chưa
    private String notes; // Ghi chú cho buổi học
}

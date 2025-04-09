package com.pbl5.autoattendance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class LessonTimeRangeDTO {
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
}

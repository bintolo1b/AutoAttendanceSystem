package com.pbl5.autoattendance.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ClassWithLessonDTO {
    @NotNull
    @NotBlank
    String name;

    @NotNull
    @Min(1)
    Integer numberOfWeeks;
    private Map<String, LessonTimeRangeDTO> schedule;
}

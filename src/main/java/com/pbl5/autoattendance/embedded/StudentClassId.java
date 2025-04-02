package com.pbl5.autoattendance.embedded;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentClassId implements Serializable {
    private Integer studentId;
    private Integer classId;
}

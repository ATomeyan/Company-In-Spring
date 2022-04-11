package com.company.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AttendanceRecordDto implements Serializable {
    private final int id;
    private final LocalDateTime entranceTime;
    private final LocalDateTime exitTime;
    private final EmployeeDto employee;
}
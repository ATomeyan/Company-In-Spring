package com.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@ToString
public class AttendanceRecordDto implements Serializable {

    private int id;
    private LocalDateTime entranceTime;
    private LocalDateTime exitTime;
    private EmployeeDto employee;
}
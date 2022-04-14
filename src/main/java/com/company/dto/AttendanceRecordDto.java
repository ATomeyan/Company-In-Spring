package com.company.dto;

import com.company.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AttendanceRecordDto implements Serializable {

    private int id;
    private LocalDateTime entranceTime;
    private LocalDateTime exitTime;
    private Employee employee;
}
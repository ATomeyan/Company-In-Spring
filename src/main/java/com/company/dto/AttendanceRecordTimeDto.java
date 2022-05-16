package com.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AttendanceRecordTimeDto implements Serializable {

    private String firstName;
    private String lastName;
    private LocalTime time;
}
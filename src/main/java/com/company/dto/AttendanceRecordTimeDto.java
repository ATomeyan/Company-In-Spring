package com.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AttendanceRecordTimeDto implements Serializable {

    private String firstName;
    private String lastName;
    private String time;
}
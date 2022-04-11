package com.company.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EmployeeDto implements Serializable {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String email;
    private final String gender;
    private final Boolean active;
    private final PositionDto positionId;
    private final DepartmentDto departmentId;
}
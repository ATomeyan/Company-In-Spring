package com.company.dto;

import com.company.entity.Department;
import com.company.entity.Position;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class EmployeeDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String gender;
    private Boolean active;
    private Position positionId;
    private Department departmentId;
}
package com.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class DepartmentDto implements Serializable {

    private int id;
    private String name;
}
package com.company.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentDto implements Serializable {
    private final int id;
    private final String name;
}
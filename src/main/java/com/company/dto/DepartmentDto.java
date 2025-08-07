package com.company.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
public class DepartmentDto implements Serializable {

    private Integer id;
    private String name;
}
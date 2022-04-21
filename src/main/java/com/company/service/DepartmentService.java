package com.company.service;

import com.company.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getByDepartmentName(String name);
}
package com.company.service;

import com.company.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getByName(String name);
}
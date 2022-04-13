package com.company.service;

import com.company.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Integer id);

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto update(Integer id, EmployeeDto employeeDto);

    void deleteEmployee(Integer id);
}
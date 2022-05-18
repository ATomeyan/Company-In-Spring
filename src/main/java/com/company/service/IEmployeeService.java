package com.company.service;

import com.company.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Integer id);

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto);

    void deleteEmployee(Integer id);
}
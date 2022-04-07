package com.company.service;

import com.company.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(int id);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(int id);
}
package com.company.service;

import com.company.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById();

    Employee saveEmployee(Employee employee);

    void deleteEmployee(int id);
}
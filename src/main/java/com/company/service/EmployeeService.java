package com.company.service;

import com.company.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    Employee saveEmployee(Employee employee);

    Employee update(int id, Employee employee);

    void deleteEmployee(int id);
}
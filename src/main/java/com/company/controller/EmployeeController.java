package com.company.controller;

import com.company.entity.Employee;
import com.company.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/company/employee")
    public List<Employee> showEmployees(){
        return service.getAllEmployees();
    }
}
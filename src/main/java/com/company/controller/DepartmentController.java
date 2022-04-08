package com.company.controller;

import com.company.entity.Department;
import com.company.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/company/department")
    public List<Department> showAll(){
        return service.getAllDepartments();
    }
}
package com.company.service.impl;

import com.company.entity.Department;
import com.company.repository.DepartmentRepository;
import com.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        Iterable<Department> iterable = repository.findAll();

        for (Department d : iterable) {
            departments.add(d);
        }

        return departments;
    }

    @Override
    public Department getByName(String name){
        return repository.findDepartmentByName(name);
    }
}
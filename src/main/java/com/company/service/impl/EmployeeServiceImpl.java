package com.company.service.impl;

import com.company.entity.Employee;
import com.company.repository.EmployeeRepository;
import com.company.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = new ArrayList<>();
        Iterable<Employee> iterable = repository.findAll();

        for (Employee e : iterable) {
            employeeList.add(e);
        }

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) {

        return repository.getById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee = repository.save(employee);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        repository.deleteById(id);
    }
}
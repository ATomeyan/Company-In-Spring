package com.company.service.impl;

import com.company.entity.Employee;
import com.company.repository.EmployeeRepository;
import com.company.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent())
            return repository.getById(id);
        else
            return null;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        employee = repository.save(employee);

        return employee;
    }

    @Override
    public Employee update(int id, Employee employee) {

        Optional<Employee> emp = repository.findById(id);
        Employee newEmployee = new Employee();

        if (emp.isPresent()) {

            newEmployee.setId(employee.getId());
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setDateOfBirth(employee.getDateOfBirth());
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setGender(employee.getGender());
            newEmployee.setActive(employee.isActive());
            newEmployee.setPositionId(employee.getPositionId());
            newEmployee.setDepartmentId(employee.getDepartmentId());
        }

        newEmployee = repository.save(employee);

        return newEmployee;
    }

    @Override
    public void deleteEmployee(int id) {

        Optional<Employee> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        }
    }
}
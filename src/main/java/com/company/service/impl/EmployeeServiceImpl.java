package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.EmployeeDto;
import com.company.entity.Employee;
import com.company.exceptions.NotFoundException;
import com.company.mapper.EmployeeMapper;
import com.company.repository.EmployeeRepository;
import com.company.service.EmployeeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger logger = (Logger) LoggerFactory.getLogger("company");
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        return getEmployeeDto(repository.findAll());
    }

    @Override
    public EmployeeDto getEmployeeById(Integer id) {

        if (id == null || id <= 0) {
            logger.info("");
            throw new IllegalArgumentException();
        }

        Employee employee = repository.getById(id);

        if (employee == null) {
            logger.error("Employee by id not found. {}", id);
            throw new NotFoundException("Employee by Id " + id + " not found.");
        }

        return employeeMapper.entityToDto(employee);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        return employeeDto;
    }

    @Override
    public EmployeeDto update(Integer id, EmployeeDto employeeDto) {

        if (id == null || id <= 0) {
            logger.info("");
            throw new IllegalArgumentException();
        }

        Employee employee = repository.getById(id);
        if (employee == null) {
            logger.error("Employee does not found. {} ", employee);
            throw new NotFoundException("Employee does not found");
        }

        Employee e = repository.save(employeeMapper.dtoToEntity(employeeDto));

        return employeeMapper.entityToDto(e);
    }

    @Override
    public EmployeeDto deleteEmployee(Integer id) {

        Optional<Employee> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        }

        return null;
    }

    private List<EmployeeDto> getEmployeeDto(List<Employee> employee) {

        List<EmployeeDto> employees = new ArrayList<>();

        for (Employee e : employee) {
            EmployeeDto employeeDto = employeeMapper.entityToDto(e);
            employees.add(employeeDto);
        }

        return employees;
    }
}
package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.EmployeeDto;
import com.company.entity.Employee;
import com.company.exceptions.AlreadyExistsException;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotValidException;
import com.company.mapper.EmployeeMapper;
import com.company.repository.EmployeeRepository;
import com.company.service.EmployeeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeServiceImpl.class);
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
            LOGGER.info("Id can't be null or less than 0: {}", id);
            throw new NotValidException("Id can't be null or less than 0.");
        }

        Employee employee = repository.findById(id).orElse(null);

        if (employee == null) {
            LOGGER.error("Employee by id not found: {}", id);
            throw new NotFoundException("Employee by Id " + id + " not found.");
        }

        return employeeMapper.entityToDto(employee);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        List<Employee> employees = repository.findByCriteria(new Employee(employeeDto.getId(),
                employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getDateOfBirth(), employeeDto.getEmail(),
                employeeDto.getGender(), employeeDto.getActive(), employeeDto.getPositionId(),
                employeeDto.getDepartmentId())).orElse(null);

        if (employees != null && !employees.isEmpty()) {
            LOGGER.warn("Employee {} already exist: ", employeeDto);
            throw new AlreadyExistsException("Employee already exist.");
        }

        Employee employee = repository.save(employeeMapper.dtoToEntity(employeeDto));

        return employeeMapper.entityToDto(employee);
    }

    @Override
    public EmployeeDto update(Integer id, EmployeeDto employeeDto) {

        if (id == null || id <= 0) {
            LOGGER.info("Id can't be null or less than 0: {}", id);
            throw new IllegalArgumentException("Id can't be null or less than 0.");
        }

        Employee employee = repository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.error("Employee does not found: {} ", id);
            throw new NotFoundException("Employee does not found");
        }

        Employee e = repository.save(employeeMapper.dtoToEntity(employeeDto));

        return employeeMapper.entityToDto(e);
    }

    @Override
    public void deleteEmployee(Integer id) {

        if (id == null || id <= 0) {
            LOGGER.error("Id can't be null or less than 0 {}", id);
            throw new IllegalArgumentException("Id can't be null or less than 0.");
        }

        Employee employee = repository.findById(id).orElse(null);

        if (employee == null) {
            LOGGER.error("Employee does not found: {} ", id);
            throw new NotFoundException("Employee does not found: {}");
        }

        repository.deleteById(id);
        LOGGER.info("Employee by id {} removed: ", id);
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
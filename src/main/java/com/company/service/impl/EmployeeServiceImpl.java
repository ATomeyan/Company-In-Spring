package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.EmployeeDto;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.entity.Position;
import com.company.exceptions.AlreadyExistsException;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotValidException;
import com.company.mapper.EmployeeMapper;
import com.company.repository.DepartmentRepository;
import com.company.repository.EmployeeRepository;
import com.company.repository.PositionRepository;
import com.company.service.EmployeeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, PositionRepository positionRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = repository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
        employeeMapper = new EmployeeMapper();
    }

    @Override
    @Transactional
    public List<EmployeeDto> getAllEmployees() {

        List<Position> positions = positionRepository.findAll();
        List<Department> departments = departmentRepository.findAll();

        return getEmployeeDto(employeeRepository.findAll());
    }

    @Override
    @Transactional
    public EmployeeDto getEmployeeById(Integer id) {

        if (id == null || id <= 0) {
            LOGGER.warn("Id can't be null or less than 0: {}", id);
            throw new NotValidException("Id can't be null or less than 0.");
        }

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            LOGGER.error("Employee by id not found: {}", id);
            throw new NotFoundException("Employee by Id " + id + " not found.");
        }

        return employeeMapper.entityToDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {

        List<Employee> employees = employeeRepository.findByCriteria(employeeMapper.dtoToEntity(employeeDto)).orElse(null);

        if (employees != null && !employees.isEmpty()) {
            LOGGER.warn("Employee {} already exist: ", employeeDto);
            throw new AlreadyExistsException("Employee already exist.");
        }

        Employee employee = employeeRepository.save(employeeMapper.dtoToEntity(employeeDto));
        LOGGER.info("Employee {} saved successfully: ", employee);

        return employeeMapper.entityToDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto) {

        if (id == null || id <= 0) {
            LOGGER.warn("Id can't be null or less than 0: {}", id);
            throw new NotValidException("Id can't be null or less than 0.");
        }

        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.error("Employee does not found: {} ", id);
            throw new NotFoundException("Employee does not found");
        }

        Employee e = employeeRepository.save(employeeMapper.dtoToEntity(employeeDto));
        LOGGER.info("Employee by id {} updated.", id);

        return employeeMapper.entityToDto(e);
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer id) {

        if (id == null || id <= 0) {
            LOGGER.warn("Id can't be null or less than 0 {}", id);
            throw new NotValidException("Id can't be null or less than 0.");
        }

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            LOGGER.error("Employee does not found: {} ", id);
            throw new NotFoundException("Employee does not found.");
        }

        employeeRepository.deleteById(id);

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
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

    private final Logger logger = (Logger) LoggerFactory.getLogger("");
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
            logger.info("Employee by Id " + id + " not found.");
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

        Optional<Employee> emp = repository.findById(id);
        EmployeeDto newEmployee = new EmployeeDto();

        if (emp.isPresent()) {

            newEmployee.setId(employeeDto.getId());
            newEmployee.setFirstName(employeeDto.getFirstName());
            newEmployee.setLastName(employeeDto.getLastName());
            newEmployee.setDateOfBirth(employeeDto.getDateOfBirth());
            newEmployee.setEmail(employeeDto.getEmail());
            newEmployee.setGender(employeeDto.getGender());
            newEmployee.setActive(employeeDto.getActive());
            newEmployee.setPositionId(employeeDto.getPositionId());
            newEmployee.setDepartmentId(employeeDto.getDepartmentId());
        }

        return newEmployee;
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

/**
 * @Transactional
 * public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
 *     if (!EmployeeValidation.isValid(employeeDto) || employeeDto.getEmployeeId() == null || employeeDto.getEmployeeId() <= 0) {
 *         LOGGER.error("The employee {} is not a valid employee", employeeDto);
 *         throw new NotValidException("The employee " + employeeDto + " is not a valid employee");
 *     }
 *
 *     Employee employee = employeeRepository.findById(employeeDto.getEmployeeId()).orElse(null);
 *     if (employee == null) {
 *         LOGGER.info("The employee {} was not found", employeeDto);
 *         throw new NotFoundException("The employee " + employeeDto + " was not found");
 *     }
 *
 *     List<Employee> employees = employeeRepository.findAllByCriteria(new Employee(employeeDto.getFName(),
 *     employeeDto.getLName(), employeeDto.getBirthday())).orElse(null);
 *
 *     if (employees != null && !employees.isEmpty()) {
 *         LOGGER.warn("The employee {} exists", employeeDto);
 *         throw new AlreadyExistsException("The employee " + employeeDto + " exists");
 *     }
 *
 *     return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
 * }
 */
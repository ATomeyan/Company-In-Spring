package com.company.mapper;

import com.company.dto.EmployeeDto;
import com.company.entity.Employee;

public class EmployeeMapper {

    public EmployeeDto entityToDto(Employee employee) {

        if (employee == null){
            throw new IllegalArgumentException();
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setGender(employee.getGender());
        employeeDto.setActive(employee.isActive()); //
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setDepartment(employee.getDepartment());

        return employeeDto;
    }

    public Employee dtoToEntity(EmployeeDto employeeDto) {

        if (employeeDto == null){
            throw new IllegalArgumentException();
        }

        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());
        employee.setActive(employeeDto.getActive());
        employee.setPosition(employeeDto.getPosition());
        employee.setDepartment(employeeDto.getDepartment());

        return employee;
    }
}
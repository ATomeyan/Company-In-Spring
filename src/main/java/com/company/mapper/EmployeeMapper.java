package com.company.mapper;

import com.company.dto.EmployeeDto;
import com.company.entity.Employee;

public class EmployeeMapper {

    public EmployeeDto entityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setGender(employee.getGender());
        employeeDto.setActive(employee.isActive());
        employeeDto.setPositionId(employee.getPositionId());
        employeeDto.setDepartmentId(employee.getDepartmentId());

        return employeeDto;
    }

    public Employee dtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setEmail(employee.getEmail());
        employee.setGender(employee.getGender());
        employee.setActive(employeeDto.getActive());
        employee.setPositionId(employeeDto.getPositionId());
        employee.setDepartmentId(employeeDto.getDepartmentId());

        return employee;
    }
}
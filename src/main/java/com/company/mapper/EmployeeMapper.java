package com.company.mapper;

import com.company.dto.DepartmentDto;
import com.company.dto.EmployeeDto;
import com.company.dto.PositionDto;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.entity.Position;

public class EmployeeMapper {

    public EmployeeDto entityToDto(Employee employee) {

        DepartmentDto departmentDto = new DepartmentDto();
        PositionDto positionDto = new PositionDto();

        if (employee == null) {
            throw new IllegalArgumentException();
        }

        if (departmentDto == null) {
            throw new IllegalArgumentException();
        }

        departmentDto.setId(employee.getDepartment().getId());
        departmentDto.setName(employee.getDepartment().getName());

        if (positionDto == null) {
            throw new IllegalArgumentException();
        }

        positionDto.setId(employee.getPosition().getId());
        positionDto.setName(employee.getPosition().getName());

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setGender(employee.getGender());
        employeeDto.setPosition(positionDto);
        employeeDto.setDepartment(departmentDto);

        return employeeDto;
    }

    public Employee dtoToEntity(EmployeeDto employeeDto) {

        if (employeeDto == null) {
            throw new IllegalArgumentException();
        }

        Position position = new Position();

        position.setId(employeeDto.getPosition().getId());
        position.setName(employeeDto.getPosition().getName());

        Department department = new Department();

        department.setId(employeeDto.getDepartment().getId());
        department.setName(employeeDto.getDepartment().getName());

        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());
        employee.setActive(employeeDto.getActive());
        employee.setPosition(position);
        employee.setDepartment(department);

        return employee;
    }
}
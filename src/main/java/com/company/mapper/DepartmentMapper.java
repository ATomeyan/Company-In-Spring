package com.company.mapper;

import com.company.dto.DepartmentDto;
import com.company.entity.Department;

public class DepartmentMapper {

    public DepartmentDto entityToDto(Department department) {

        if (department == null){
            throw new IllegalArgumentException();
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());

        return departmentDto;
    }

    public Department dtoToEntity(DepartmentDto departmentDto) {

        if (departmentDto == null){
            throw new IllegalArgumentException();
        }

        Department department = new Department();

        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());

        return department;
    }
}
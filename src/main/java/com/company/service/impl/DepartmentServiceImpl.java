package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.DepartmentDto;
import com.company.entity.Department;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotValidException;
import com.company.mapper.DepartmentMapper;
import com.company.repository.DepartmentRepository;
import com.company.service.DepartmentService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
        departmentMapper = new DepartmentMapper();
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return getDepartmentDto(repository.findAll());
    }

    @Override
    public DepartmentDto getByDepartmentName(String name) {

        if (name == null || name.isEmpty()) {
            LOGGER.error("Department name is not valid {}.", name);
            throw new NotValidException("Department name is not valid.");
        }

        Department department = repository.findDepartmentByName(name).orElse(null);
        if (department == null) {
            LOGGER.error("Department by name {} is not found.", name);
            throw new NotFoundException("Department is not found.");
        }

        return departmentMapper.entityToDto(department);
    }

    private List<DepartmentDto> getDepartmentDto(List<Department> department) {

        List<DepartmentDto> departments = new ArrayList<>();

        for (Department d : department) {
            DepartmentDto departmentDto = departmentMapper.entityToDto(d);
            departments.add(departmentDto);
        }

        return departments;
    }
}
package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.EmployeeDto;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotGeneratedException;
import com.company.service.EmployeeService;
import com.company.util.ExcelExporter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author Artur Tomeyan
 * @date 05/09/2022
 */
@Service
public class ExcelGenerateService implements com.company.service.ExcelGenerateService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ExcelGenerateService.class);
    private final EmployeeService employeeService;

    @Autowired
    public ExcelGenerateService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ByteArrayInputStream load() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            LOGGER.error("Employee list is empty {}", employees);
            throw new NotFoundException("Employee list is empty");
        }

        ByteArrayInputStream byteArrayInputStream = ExcelExporter.employeesToExcel(employees);

        if (byteArrayInputStream == null) {
            LOGGER.error("Excel is not generated {}", byteArrayInputStream);
            throw new NotGeneratedException("Excel is not generated");
        }

        LOGGER.info("Excel file is generated successfully");
        return byteArrayInputStream;
    }
}
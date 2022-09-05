package com.company.util;

import com.company.dto.EmployeeDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Artur Tomeyan
 * @date 05/09/2022
 */
public class ExcelExporter {

    private static final String[] HEADERS = {
            "First name",
            "Last name",
            "Date of birth",
            "Email",
            "Gender",
            "Position",
            "Department"
    };
    private static final String SHEET = "Employees";

    private ExcelExporter() {
    }

    public static ByteArrayInputStream employeesToExcel(List<EmployeeDto> employeeDtos) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIdx = 1;
            for (EmployeeDto employeeDto : employeeDtos) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(employeeDto.getFirstName());
                row.createCell(1).setCellValue(employeeDto.getLastName());
                row.createCell(2).setCellValue(employeeDto.getDateOfBirth().toString());
                row.createCell(3).setCellValue(employeeDto.getEmail());
                row.createCell(4).setCellValue(employeeDto.getGender());
                row.createCell(5).setCellValue(employeeDto.getPosition().getName());
                row.createCell(6).setCellValue(employeeDto.getDepartment().getName());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
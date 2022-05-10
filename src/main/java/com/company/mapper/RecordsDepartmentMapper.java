package com.company.mapper;

import com.company.dto.RecordsDepartmentDto;
import com.company.entity.AttendanceRecord;
import com.company.entity.Department;
import com.company.entity.Employee;

public class RecordsDepartmentMapper {

    public AttendanceRecord dtoToEntity(RecordsDepartmentDto recordsDepartmentDto) {

        if (recordsDepartmentDto == null) {
            throw new IllegalArgumentException();
        }

        Department department = new Department();

        department.setId(recordsDepartmentDto.getDepartment().getId());
        department.setName(recordsDepartmentDto.getDepartment().getName());

        Employee employee = new Employee();

        employee.setDepartment(department);

        AttendanceRecord attendanceRecord = new AttendanceRecord();

        attendanceRecord.setId(recordsDepartmentDto.getId());
        attendanceRecord.setEntranceTime(recordsDepartmentDto.getEntranceTime());
        attendanceRecord.setExitTime(recordsDepartmentDto.getExitTime());
        attendanceRecord.setEmployee(employee);

        return attendanceRecord;
    }
}
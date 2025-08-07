package com.company.mapper;

import com.company.dto.AttendanceRecordDto;
import com.company.dto.DepartmentDto;
import com.company.dto.EmployeeDto;
import com.company.dto.PositionDto;
import com.company.entity.AttendanceRecord;

public class AttendanceRecordMapper {

    public AttendanceRecordDto entityToDto(AttendanceRecord attendanceRecord) {

        if (attendanceRecord == null) {
            throw new IllegalArgumentException();
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId(attendanceRecord.getEmployee().getDepartment().getId());
        departmentDto.setName(attendanceRecord.getEmployee().getDepartment().getName());

        PositionDto positionDto = new PositionDto();

        positionDto.setId(attendanceRecord.getEmployee().getPosition().getId());
        positionDto.setName(attendanceRecord.getEmployee().getPosition().getName());

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(attendanceRecord.getEmployee().getId());
        employeeDto.setFirstName(attendanceRecord.getEmployee().getFirstName());
        employeeDto.setLastName(attendanceRecord.getEmployee().getLastName());
        employeeDto.setDateOfBirth(attendanceRecord.getEmployee().getDateOfBirth());
        employeeDto.setEmail(attendanceRecord.getEmployee().getEmail());
        employeeDto.setGender(attendanceRecord.getEmployee().getGender());
        employeeDto.setActive(attendanceRecord.getEmployee().getActive());
        employeeDto.setPosition(positionDto);
        employeeDto.setDepartment(departmentDto);

        AttendanceRecordDto attendanceRecordDto = new AttendanceRecordDto();

        attendanceRecordDto.setId(attendanceRecord.getId());
        attendanceRecordDto.setEntranceTime(attendanceRecord.getEntranceTime());
        attendanceRecordDto.setExitTime(attendanceRecord.getExitTime());
        attendanceRecordDto.setEmployee(employeeDto);

        return attendanceRecordDto;
    }
}
package com.company.mapper;

import com.company.dto.AttendanceRecordDto;
import com.company.dto.DepartmentDto;
import com.company.dto.EmployeeDto;
import com.company.dto.PositionDto;
import com.company.entity.AttendanceRecord;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.entity.Position;
import lombok.Data;

@Data
public class AttendanceRecordMapper {

    public AttendanceRecordDto entityToDto(AttendanceRecord attendanceRecord) {

        if (attendanceRecord == null){
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
        employeeDto.setActive(attendanceRecord.getEmployee().isActive());
        employeeDto.setPosition(positionDto);
        employeeDto.setDepartment(departmentDto);

        AttendanceRecordDto attendanceRecordDto = new AttendanceRecordDto();

        attendanceRecordDto.setId(attendanceRecord.getId());
        attendanceRecordDto.setEntranceTime(attendanceRecord.getEntranceTime());
        attendanceRecordDto.setExitTime(attendanceRecord.getExitTime());
        attendanceRecordDto.setEmployee(employeeDto);

        return attendanceRecordDto;
    }

    public AttendanceRecord dtoToEntity(AttendanceRecordDto attendanceRecordDto) {

        if (attendanceRecordDto == null){
            throw new IllegalArgumentException();
        }

        Position position = new Position();

        position.setId(attendanceRecordDto.getEmployee().getPosition().getId());
        position.setName(attendanceRecordDto.getEmployee().getPosition().getName());

        Department department = new Department();

        department.setId(attendanceRecordDto.getEmployee().getDepartment().getId());
        department.setName(attendanceRecordDto.getEmployee().getDepartment().getName());

        Employee employee = new Employee();

        employee.setId(attendanceRecordDto.getEmployee().getId());
        employee.setFirstName(attendanceRecordDto.getEmployee().getFirstName());
        employee.setLastName(attendanceRecordDto.getEmployee().getLastName());
        employee.setDateOfBirth(attendanceRecordDto.getEmployee().getDateOfBirth());
        employee.setEmail(attendanceRecordDto.getEmployee().getEmail());
        employee.setGender(attendanceRecordDto.getEmployee().getGender());
        employee.setActive(attendanceRecordDto.getEmployee().getActive());
        employee.setPosition(position);
        employee.setDepartment(department);

        AttendanceRecord attendanceRecord = new AttendanceRecord();

        attendanceRecord.setId(attendanceRecordDto.getId());
        attendanceRecord.setEntranceTime(attendanceRecordDto.getEntranceTime());
        attendanceRecord.setExitTime(attendanceRecordDto.getExitTime());
        attendanceRecord.setEmployee(employee);

        return attendanceRecord;
    }
}
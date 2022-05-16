package com.company.mapper;

import com.company.dto.AttendanceRecordTimeDto;
import com.company.entity.AttendanceRecord;

public class AttendanceRecordTimeMapper {

    public AttendanceRecordTimeDto entityToDto(AttendanceRecord attendanceRecord) {

        if (attendanceRecord == null) {
            throw new IllegalArgumentException();
        }

        AttendanceRecordTimeDto attendanceRecordTimeDto = new AttendanceRecordTimeDto();

        attendanceRecordTimeDto.setFirstName(attendanceRecord.getEmployee().getFirstName());
        attendanceRecordTimeDto.setLastName(attendanceRecord.getEmployee().getLastName());

        return attendanceRecordTimeDto;
    }
}
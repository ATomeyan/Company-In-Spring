package com.company.mapper;

import com.company.dto.AttendanceRecordDto;
import com.company.entity.AttendanceRecord;
import lombok.Data;

@Data
public class AttendanceRecordMapper {

    public AttendanceRecordDto entityToDto(AttendanceRecord attendanceRecord) {
        AttendanceRecordDto attendanceRecordDto = new AttendanceRecordDto();

        attendanceRecordDto.setId(attendanceRecord.getId());
        attendanceRecordDto.setEntranceTime(attendanceRecord.getEntranceTime());
        attendanceRecordDto.setExitTime(attendanceRecord.getExitTime());
        attendanceRecordDto.setEmployee(attendanceRecord.getEmployee());

        return attendanceRecordDto;
    }

    public AttendanceRecord dtoToEntity(AttendanceRecordDto attendanceRecordDto) {
        AttendanceRecord attendanceRecord = new AttendanceRecord();

        attendanceRecord.setId(attendanceRecordDto.getId());
        attendanceRecord.setEntranceTime(attendanceRecordDto.getEntranceTime());
        attendanceRecord.setExitTime(attendanceRecordDto.getExitTime());
        attendanceRecord.setEmployee(attendanceRecordDto.getEmployee());

        return attendanceRecord;
    }
}
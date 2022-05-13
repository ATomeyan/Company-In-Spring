package com.company.mapper;

import com.company.dto.AttendanceRecordTimeDto;
import com.company.entity.AttendanceRecord;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AttendanceRecordTimeMapper {

    public AttendanceRecordTimeDto entityToDto(AttendanceRecord attendanceRecord) {

        if (attendanceRecord == null) {
            throw new IllegalArgumentException();
        }

        AttendanceRecordTimeDto attendanceRecordTimeDto = new AttendanceRecordTimeDto();

        attendanceRecordTimeDto.setFirstName(attendanceRecord.getEmployee().getFirstName());
        attendanceRecordTimeDto.setLastName(attendanceRecord.getEmployee().getLastName());
        attendanceRecordTimeDto.setTime(getTime(attendanceRecord.getEntranceTime(), attendanceRecord.getExitTime()));

        return attendanceRecordTimeDto;
    }

    private String getTime(LocalDateTime from, LocalDateTime to) {

        long hours = ChronoUnit.HOURS.between(from, to);
        long minutes = ChronoUnit.MINUTES.between(from, to) % 60;
        long s = ChronoUnit.SECONDS.between(from, to) % 60;

        return hours + ":" + minutes + ":" + s;
    }
}
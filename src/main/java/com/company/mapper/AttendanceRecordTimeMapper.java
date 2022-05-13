package com.company.mapper;

import com.company.dto.AttendanceRecordTimeDto;
import com.company.entity.AttendanceRecord;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

        List<Long> hours = new ArrayList<>();
        Duration duration = Duration.between(from, to);
        long h = duration.toHours();
        hours.add(h);

        long m = ChronoUnit.MINUTES.between(from, to);
        return hours + ":";
    }
}
/*
        Long sum = 0L;
        List<Long> time = new LinkedList<>();
        Duration duration = Duration.between(from, to);

        time.add(duration.toHours());

        for (Long t: time) {
            sum = t + t;
        }

        return sum.toString();

        int MINUTES_PER_HOUR = 60;
        int SECONDS_PER_MINUTE = 60;
        int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

        LocalDateTime today = LocalDateTime.of(to.getYear(), to.getMonthValue(), to.getDayOfMonth(), from.getHour(), from.getMinute(), from.getSecond());
        Duration duration = Duration.between(today, to);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long sec = (seconds % SECONDS_PER_MINUTE);

        return hours + ":" + minutes + ":" + sec;
 */
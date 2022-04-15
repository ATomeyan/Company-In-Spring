package com.company.service;

import com.company.dto.AttendanceRecordDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRecordService {

    List<AttendanceRecordDto> getByDateTime(LocalDateTime entranceDate, String name);
}
package com.company.service;

import com.company.entity.AttendanceRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRecordService {

    List<AttendanceRecord> getAllRecords();

    List<AttendanceRecord> getByDateTime(LocalDateTime dateTime);
}
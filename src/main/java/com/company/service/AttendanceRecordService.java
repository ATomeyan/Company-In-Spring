package com.company.service;

import com.company.entity.AttendanceRecord;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordService {

    List<AttendanceRecord> getAllRecords();

    List<AttendanceRecord> getByDateTimeAndName(LocalDate date, String departmentName);
}
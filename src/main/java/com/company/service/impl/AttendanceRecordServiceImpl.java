package com.company.service.impl;

import com.company.entity.AttendanceRecord;
import com.company.repository.AttendanceRecordRepository;
import com.company.service.AttendanceRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    private final AttendanceRecordRepository repository;

    public AttendanceRecordServiceImpl(AttendanceRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AttendanceRecord> getAllRecords() {
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        Iterable<AttendanceRecord> iterable = repository.findAll();

        for (AttendanceRecord r : iterable) {
            attendanceRecords.add(r);
        }

        return attendanceRecords;
    }

    @Override
    public List<AttendanceRecord> getByDateTimeAndName(LocalDate date, String departmentName) {
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        Iterable<AttendanceRecord> iterable = repository.findRecordByEntranceTimeAndDepartmentName(date, departmentName);

        for (AttendanceRecord r : iterable) {
            attendanceRecords.add(r);
        }

        return attendanceRecords;
    }
}
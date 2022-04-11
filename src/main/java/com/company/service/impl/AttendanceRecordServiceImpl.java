package com.company.service.impl;

import com.company.entity.AttendanceRecord;
import com.company.repository.AttendanceRecordRepository;
import com.company.service.AttendanceRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    private final AttendanceRecordRepository repository;

    public AttendanceRecordServiceImpl(AttendanceRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AttendanceRecord> getByDateTimeAndName(LocalDateTime entranceDate, LocalDateTime exitDate, String departmentName) {

        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        Iterable<AttendanceRecord> iterable = repository.findRecordByEntranceTimeAndDepartmentName(entranceDate, exitDate, departmentName);

        for (AttendanceRecord r : iterable) {
            attendanceRecords.add(r);
        }

        return attendanceRecords;
    }
}
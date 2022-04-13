package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.entity.AttendanceRecord;
import com.company.mapper.AttendanceRecordMapper;
import com.company.repository.AttendanceRecordRepository;
import com.company.service.AttendanceRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    private final Logger logger = (Logger) LoggerFactory.getLogger("company.record");
    private final AttendanceRecordMapper mapper = new AttendanceRecordMapper();
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
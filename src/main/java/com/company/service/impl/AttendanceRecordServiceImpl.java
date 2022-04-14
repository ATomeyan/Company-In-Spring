package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.AttendanceRecordDto;
import com.company.entity.AttendanceRecord;
import com.company.mapper.AttendanceRecordMapper;
import com.company.repository.AttendanceRecordRepository;
import com.company.service.AttendanceRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AttendanceRecordServiceImpl.class);
    private final AttendanceRecordMapper recordMapper;
    private final AttendanceRecordRepository repository;

    @Autowired
    public AttendanceRecordServiceImpl(AttendanceRecordRepository repository) {
        this.repository = repository;
        recordMapper = new AttendanceRecordMapper();
    }

    @Override
    public List<AttendanceRecordDto> getByDateTime(LocalDateTime entranceDate, LocalDateTime exitDate) {


    }

    private List<AttendanceRecordDto> getRecordDto(List<AttendanceRecord> record) {

        List<AttendanceRecordDto> records = new ArrayList<>();

        for (AttendanceRecord r : record) {
            AttendanceRecordDto recordDto = recordMapper.entityToDto(r);
            records.add(recordDto);
        }

        return records;
    }
}
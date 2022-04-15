package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.AttendanceRecordDto;
import com.company.entity.AttendanceRecord;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotValidException;
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
    public List<AttendanceRecordDto> getByDateTime(LocalDateTime dateTime, String name) {

        if (dateTime == null || name.isEmpty()){
            LOGGER.error("The date time {} or name {} is not valid. ", dateTime, name);
            throw new NotValidException("The date time or name is not valid.");
        }

        List<AttendanceRecord> records = repository.findRecordByDateTime(dateTime, name).orElse(null);
        if (records == null || records.isEmpty()){
            LOGGER.error("Records is not found {}. ", records);
            throw new NotFoundException("Records is not found.");
        }

        return getRecordDto(records);
    }

    private List<AttendanceRecordDto> getRecordDto(List<AttendanceRecord> records) {

        List<AttendanceRecordDto> dtoList = new ArrayList<>();

        for (AttendanceRecord r : records) {
            AttendanceRecordDto recordDto = recordMapper.entityToDto(r);
            dtoList.add(recordDto);
        }

        return dtoList;
    }
}
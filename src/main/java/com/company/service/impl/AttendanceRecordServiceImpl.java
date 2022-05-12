package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.AttendanceRecordDto;
import com.company.dto.AttendanceRecordTimeDto;
import com.company.dto.RecordsDepartmentDto;
import com.company.entity.AttendanceRecord;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotValidException;
import com.company.mapper.AttendanceRecordMapper;
import com.company.mapper.AttendanceRecordTimeMapper;
import com.company.mapper.RecordsDepartmentMapper;
import com.company.repository.AttendanceRecordRepository;
import com.company.service.AttendanceRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AttendanceRecordServiceImpl.class);
    private final AttendanceRecordMapper recordMapper;
    private final RecordsDepartmentMapper recordsDepartmentMapper;
    private final AttendanceRecordTimeMapper recordTimeMapper;
    private final AttendanceRecordRepository repository;

    @Autowired
    public AttendanceRecordServiceImpl(AttendanceRecordRepository repository) {
        this.repository = repository;
        recordMapper = new AttendanceRecordMapper();
        recordsDepartmentMapper = new RecordsDepartmentMapper();
        recordTimeMapper = new AttendanceRecordTimeMapper();
    }

    @Override
    public List<AttendanceRecordDto> getRecordsByCriteria(RecordsDepartmentDto recordsDepartmentDto) {

        if (recordsDepartmentDto == null) {
            LOGGER.error("The date time {} is not valid. ", recordsDepartmentDto);
            throw new NotValidException("The date time is not valid.");
        }

        List<AttendanceRecord> records = repository.findRecordByCriteria(recordsDepartmentMapper.dtoToEntity(recordsDepartmentDto)).orElse(null);
        if (records == null || records.isEmpty()) {
            LOGGER.error("Records is not found {}. ", records);
            throw new NotFoundException("Records is not found.");
        }

        return getRecordDto(records);
    }

    @Override
    public AttendanceRecordTimeDto getRecordTimeCounter(RecordsDepartmentDto recordsDepartmentDto) {

        if (recordsDepartmentDto == null) {
            LOGGER.error("The date time {} is not valid. ", recordsDepartmentDto);
            throw new NotValidException("The date time is not valid.");
        }

        AttendanceRecord records = repository.findRecordBy(recordsDepartmentMapper.dtoToEntity(recordsDepartmentDto)).orElse(null);
        if (records == null) {
            LOGGER.error("Records is not found {}. ", records);
            throw new NotFoundException("Records is not found.");
        }

        AttendanceRecordTimeDto attendanceRecordTimeDto = recordTimeMapper.entityToDto(records);
        attendanceRecordTimeDto.setTime(getTime(records.getEntranceTime(), records.getExitTime()));

        return attendanceRecordTimeDto;
    }

    private List<AttendanceRecordDto> getRecordDto(List<AttendanceRecord> records) {

        List<AttendanceRecordDto> dtoList = new ArrayList<>();

        for (AttendanceRecord r : records) {
            AttendanceRecordDto recordDto = recordMapper.entityToDto(r);
            dtoList.add(recordDto);
        }

        return dtoList;
    }

    private String getTime(LocalDateTime from, LocalDateTime to) {

        long hours = ChronoUnit.HOURS.between(from, to);
        long minutes = ChronoUnit.MINUTES.between(from, to) % 60;
        long seconds = ChronoUnit.SECONDS.between(from, to) % 60;

        return hours + ":" + minutes + ":" + seconds;
    }
}
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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AttendanceRecordService implements com.company.service.AttendanceRecordService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AttendanceRecordService.class);
    private final AttendanceRecordMapper recordMapper;
    private final RecordsDepartmentMapper recordsDepartmentMapper;
    private final AttendanceRecordTimeMapper recordTimeMapper;
    private final AttendanceRecordRepository repository;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepository repository) {
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
    public List<AttendanceRecordTimeDto> getRecordTimeCounter(RecordsDepartmentDto recordsDepartmentDto) {

        if (recordsDepartmentDto == null) {
            LOGGER.error("The date time {} is not valid. ", recordsDepartmentDto);
            throw new NotValidException("The date time is not valid.");
        }

        List<AttendanceRecord> records = repository.findRecordByCriteria(recordsDepartmentMapper.dtoToEntity(recordsDepartmentDto)).orElse(null);

        if (records == null) {
            LOGGER.error("Records is not found {}. ", records);
            throw new NotFoundException("Records is not found.");
        }

        return getTimeRecordDto(records);
    }

    private List<AttendanceRecordTimeDto> getTimeRecordDto(List<AttendanceRecord> records) {

        List<AttendanceRecordTimeDto> dtoList = new ArrayList<>();
        Map<Integer, Long> map = new HashMap<>();

        for (AttendanceRecord record : records) {
            long millis = Duration.between(record.getEntranceTime(), record.getExitTime()).toMillis();
            if (map.containsKey(record.getEmployee().getId())) {
                map.put(record.getEmployee().getId(), millis + map.get(record.getEmployee().getId()));
            } else {
                map.put(record.getEmployee().getId(), millis);
            }
        }

        map.forEach((key, value) -> {
            AttendanceRecordTimeDto recordTimeDto = new AttendanceRecordTimeDto();

            long HH = TimeUnit.MILLISECONDS.toHours(value);
            long MM = TimeUnit.MILLISECONDS.toMinutes(value) % 60;
            long SS = TimeUnit.MILLISECONDS.toSeconds(value) % 60;

            for (AttendanceRecord r : records) {
                if (Objects.equals(key, r.getEmployee().getId())) {
                    recordTimeDto = recordTimeMapper.entityToDto(r);
                    recordTimeDto.setTime(LocalTime.of((int) HH, (int) MM, (int) SS));
                }
            }
            dtoList.add(recordTimeDto);
        });

        return dtoList;
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
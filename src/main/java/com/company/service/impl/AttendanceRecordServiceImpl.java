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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        AttendanceRecordTimeDto recordTimeDto = new AttendanceRecordTimeDto();
        List<AttendanceRecordTimeDto> dtoList = new ArrayList<>();
        Map<Integer, LocalTime> map = new HashMap<>();

        for (AttendanceRecord r : records) {

            Duration date = Duration.between(r.getEntranceTime(), r.getExitTime());
            LocalTime hour = LocalTime.of((int) date.toHours(), (int) date.toMinutes() % 60, (int) date.getSeconds() % 60);
            LocalTime total = LocalTime.now();
            map.put(r.getId(), hour);

            if (map.containsKey(r.getId())) {

                LocalTime time = map.get(r.getId());
                total = time.plusHours(time.getHour()).plusMinutes(time.getMinute()).plusSeconds(time.getSecond());
            }

            recordTimeDto.setTime(total);
            recordTimeDto = recordTimeMapper.entityToDto(r);
            dtoList.add(recordTimeDto);
        }

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

    private Duration getTime(LocalDateTime from, LocalDateTime to) {

        Duration duration = Duration.between(from, to);

//        long h = ChronoUnit.HOURS.between(from, to);
//        long m = ChronoUnit.MINUTES.between(from, to) / 60;
//        long s = ChronoUnit.SECONDS.between(from, to) % 60;
//        long h = duration.toHours();
//        long m = duration.toMinutes();
//        long s = duration.getSeconds();

        return duration;
    }
}
package com.company.controller;

import com.company.dto.AttendanceRecordDto;
import com.company.dto.AttendanceRecordTimeDto;
import com.company.dto.RecordsDepartmentDto;
import com.company.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/records")
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    @Autowired
    public AttendanceRecordController(AttendanceRecordService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ResponseEntity<List<AttendanceRecordDto>> getByCriteria(@RequestBody RecordsDepartmentDto recordsDepartmentDto) {

        List<AttendanceRecordDto> dto = service.getRecordsByCriteria(recordsDepartmentDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/count")
    public ResponseEntity<AttendanceRecordTimeDto> getTimeCounter(@RequestBody RecordsDepartmentDto recordsDepartmentDto) {
        AttendanceRecordTimeDto dto = service.getRecordTimeCounter(recordsDepartmentDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
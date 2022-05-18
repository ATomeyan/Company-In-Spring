package com.company.controller;

import com.company.dto.AttendanceRecordDto;
import com.company.dto.AttendanceRecordTimeDto;
import com.company.dto.RecordsDepartmentDto;
import com.company.service.IAttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/records")
public class AttendanceRecordController {

    private final IAttendanceRecordService service;

    @Autowired
    public AttendanceRecordController(IAttendanceRecordService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ResponseEntity<List<AttendanceRecordDto>> getByCriteria(@RequestBody RecordsDepartmentDto recordsDepartmentDto) {

        List<AttendanceRecordDto> dto = service.getRecordsByCriteria(recordsDepartmentDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/count")
    public ResponseEntity<List<AttendanceRecordTimeDto>> getTimeCounter(@RequestBody RecordsDepartmentDto recordsDepartmentDto) {
        List<AttendanceRecordTimeDto> dto = service.getRecordTimeCounter(recordsDepartmentDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
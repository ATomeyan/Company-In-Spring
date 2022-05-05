package com.company.controller;

import com.company.dto.AttendanceRecordDto;
import com.company.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/{from}/{to}/{depId}")
    public ResponseEntity<List<AttendanceRecordDto>> getByCriteria(@PathVariable("from") LocalDateTime from,
                                                                   @PathVariable("to") LocalDateTime to,
                                                                   @PathVariable("depId") Integer depId) {

        List<AttendanceRecordDto> dto = service.getByDateTime(from, to, depId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceRecordDto>> getRecords(){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<AttendanceRecordDto>> countByCriteria(@PathVariable("from") LocalDateTime from,
                                                           @PathVariable("to") LocalDateTime to,
                                                           @PathVariable("empId") Integer empId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AttendanceRecordDto> getCounter(){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
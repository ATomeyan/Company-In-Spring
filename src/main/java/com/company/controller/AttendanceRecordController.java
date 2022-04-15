package com.company.controller;

import com.company.dto.AttendanceRecordDto;
import com.company.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/records")
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    @Autowired
    public AttendanceRecordController(AttendanceRecordService service) {
        this.service = service;
    }

    @GetMapping("/{date}/{name}")
    public ResponseEntity<List<AttendanceRecordDto>> getByDate(@PathVariable("date") LocalDateTime dateTime, @PathVariable("name") String name){

        List<AttendanceRecordDto> dto = service.getByDateTime(dateTime, name);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
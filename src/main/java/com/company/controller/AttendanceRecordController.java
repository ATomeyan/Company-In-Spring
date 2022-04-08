package com.company.controller;

import com.company.entity.AttendanceRecord;
import com.company.service.AttendanceRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    public AttendanceRecordController(AttendanceRecordService service) {
        this.service = service;
    }

    @GetMapping("/company/records")
    public List<AttendanceRecord> recordList(){
        return service.getAllRecords();
    }
}
package com.company.controller;

import com.company.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    @Autowired
    public AttendanceRecordController(AttendanceRecordService service) {
        this.service = service;
    }
}
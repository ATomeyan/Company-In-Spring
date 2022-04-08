package com.company.controller;

import com.company.entity.AttendanceRecord;
import com.company.service.AttendanceRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    public AttendanceRecordController(AttendanceRecordService service) {
        this.service = service;
    }

    @GetMapping
    public List<AttendanceRecord> gerRecords() {
        return service.getAllRecords();
    }

    @GetMapping("/{date}/{department}")
    public List<AttendanceRecord> getRecordsByDate(
            @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
            @PathVariable("department") String department) {

        return service.getByDateTimeAndName(date, department);
    }
}
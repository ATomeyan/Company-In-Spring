package com.company.controller;

import com.company.entity.AttendanceRecord;
import com.company.service.AttendanceRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/records")
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    public AttendanceRecordController(AttendanceRecordService service) {
        this.service = service;
    }

    @GetMapping("/{dateIn}/{dateOut}/{department}")
    public List<AttendanceRecord> getRecordsByDate(
            @RequestParam("dateIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime entranceDate,
            @RequestParam("dateOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime exitDate,
            @RequestParam("department") String department) {

        return service.getByDateTimeAndName(entranceDate, exitDate, department);
    }
}
package com.company.service;

import com.company.dto.AttendanceRecordDto;
import com.company.dto.RecordsDepartmentDto;

import java.util.List;

public interface AttendanceRecordService {

    List<AttendanceRecordDto> getRecordsByCriteria(RecordsDepartmentDto recordsDepartmentDto);
}
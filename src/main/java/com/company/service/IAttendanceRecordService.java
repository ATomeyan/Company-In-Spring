package com.company.service;

import com.company.dto.AttendanceRecordDto;
import com.company.dto.AttendanceRecordTimeDto;
import com.company.dto.RecordsDepartmentDto;

import java.util.List;

public interface IAttendanceRecordService {

    List<AttendanceRecordDto> getRecordsByCriteria(RecordsDepartmentDto recordsDepartmentDto);

    List<AttendanceRecordTimeDto> getRecordTimeCounter(RecordsDepartmentDto recordsDepartmentDto);
}
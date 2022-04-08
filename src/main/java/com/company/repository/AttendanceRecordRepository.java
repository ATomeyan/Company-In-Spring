package com.company.repository;

import com.company.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    List<AttendanceRecord> findRecordByEntranceTimeAndDepartmentName(LocalDate date, String departmentName);
}
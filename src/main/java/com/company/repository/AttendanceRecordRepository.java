package com.company.repository;

import com.company.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    @Query("select a from AttendanceRecord a join Department d where a.entranceTime = :entranceDate and a.entranceTime = :exitDate and d.name = :name")
    List<AttendanceRecord> findRecordByEntranceTimeAndDepartmentName(
            @Param("entranceDate") LocalDateTime entranceDate,
            @Param("exitDate") LocalDateTime exitDate,
            @Param("name") String departmentName);
}
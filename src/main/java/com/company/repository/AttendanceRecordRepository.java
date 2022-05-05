package com.company.repository;

import com.company.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    @Query("from AttendanceRecord r join Department d on r.entranceTime >= :from and r.exitTime <= :to and d.id = :depId")
    Optional<List<AttendanceRecord>> findRecordByCriteria(
            @Param("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @Param("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @Param("depId") Integer depId);
}
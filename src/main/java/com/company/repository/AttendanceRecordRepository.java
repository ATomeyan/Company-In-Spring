package com.company.repository;

import com.company.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    @Query("from AttendanceRecord where entranceTime = :entranceDate and exitTime = :exitDate")
    List<AttendanceRecord> findRecordByDateTime(
            @Param("entranceDate") LocalDateTime entranceDate,
            @Param("exitDate") LocalDateTime exitDate);
}
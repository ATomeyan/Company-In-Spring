package com.company.repository;

import com.company.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    @Query("FROM AttendanceRecord r join Department d on d.id=:depId where r.entranceTime BETWEEN :from AND :to")
    Optional<List<AttendanceRecord>> findRecordByCriteria(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("depId") Integer depId);
}
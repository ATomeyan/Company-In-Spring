package com.company.repository;

import com.company.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    @Query("FROM AttendanceRecord r join Department d on d.id=:#{#record.employee.department.id} where " +
            ":#{#record.entranceTime} BETWEEN :#{#record.entranceTime} AND :#{#record.exitTime}")
    Optional<List<AttendanceRecord>> findRecordByCriteria(AttendanceRecord record);
}
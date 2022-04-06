package com.company.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "attendance_record")
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "entrance_time", nullable = false)
    private Instant entranceTime;

    @Column(name = "exit_time", nullable = false)
    private Instant exitTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public AttendanceRecord() {
    }

    public AttendanceRecord(Integer id, Instant entranceTime, Instant exitTime, Employee employee) {
        this.id = id;
        this.entranceTime = entranceTime;
        this.exitTime = exitTime;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Instant getExitTime() {
        return exitTime;
    }

    public void setExitTime(Instant exitTime) {
        this.exitTime = exitTime;
    }

    public Instant getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(Instant entranceTime) {
        this.entranceTime = entranceTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "id=" + id +
                ", entranceTime=" + entranceTime +
                ", exitTime=" + exitTime +
                ", employee=" + employee +
                '}';
    }
}
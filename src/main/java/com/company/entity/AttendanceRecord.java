package com.company.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_record")
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "entrance_time")
    private LocalDateTime entranceTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public AttendanceRecord() {
    }

    public AttendanceRecord(Integer id, LocalDateTime entranceTime, LocalDateTime exitTime, Employee employee) {
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

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public LocalDateTime getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(LocalDateTime entranceTime) {
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
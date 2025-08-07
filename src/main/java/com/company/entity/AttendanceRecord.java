package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "attendance_record")
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "entrance_time")
    private LocalDateTime entranceTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AttendanceRecord that = (AttendanceRecord) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(entranceTime, that.entranceTime)
                .append(exitTime, that.exitTime)
                .append(employee, that.employee)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(entranceTime)
                .append(exitTime)
                .append(employee)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("entranceTime", entranceTime)
                .append("exitTime", exitTime)
                .append("employee", employee)
                .toString();
    }
}
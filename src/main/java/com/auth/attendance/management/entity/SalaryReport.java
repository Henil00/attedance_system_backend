package com.auth.attendance.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "salary_reports")
@Data
public class SalaryReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sr_no")
    private Integer srNo;

    @Column(name = "employee_sr_no", nullable = false)
    private Integer employeeSrNo;

    @Column(name = "employee_name", length = 100)
    private String employeeName;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "present_days", nullable = false)
    private Integer presentDays;

    @Column(name = "half_days", nullable = false)
    private Integer halfDays;

    @Column(name = "absent_days", nullable = false)
    private Integer absentDays;

    @Column(name = "total_overtime_hours", precision = 10, scale = 2)
    private BigDecimal totalOvertimeHours;

    @Column(name = "present_salary", precision = 10, scale = 2, nullable = false)
    private BigDecimal presentSalary;

    @Column(name = "overtime_rate_per_hour", precision = 10, scale = 2, nullable = false)
    private BigDecimal overtimeRatePerHour;

    @Column(name = "half_day_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal halfDayAmount;

    @Column(name = "overtime_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal overtimeAmount;

    @Column(name = "total_salary", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalSalary;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
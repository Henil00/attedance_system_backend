package com.auth.attendance.management.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalaryReportResponse {
    private Integer srNo;
    private Integer employeeSrNo;
    private String employeeName;
    private Integer month;
    private Integer year;
    private Integer presentDays;
    private Integer halfDays;
    private Integer absentDays;
    private BigDecimal totalOvertimeHours;
    private BigDecimal presentSalary;
    private BigDecimal overtimeRatePerHour;
    private BigDecimal halfDayAmount;
    private BigDecimal overtimeAmount;
    private BigDecimal totalSalary;
    private LocalDateTime createdAt;
}
package com.auth.attendance.management.dto.request;

import com.auth.attendance.management.enums.AttendanceStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AttendanceRequest {
    private Integer employeeSrNo;
    private LocalDate attendanceDate;
    private AttendanceStatus status;
    private BigDecimal overtimeHours;
    private String notes;
}
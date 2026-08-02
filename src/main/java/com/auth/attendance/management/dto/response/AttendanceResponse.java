package com.auth.attendance.management.dto.response;

import com.auth.attendance.management.enums.AttendanceStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceResponse {
    private Integer srNo;
    private Integer employeeSrNo;
    private LocalDate attendanceDate;
    private AttendanceStatus status;
    private BigDecimal overtimeHours;
    private String notes;
    private LocalDateTime createdAt;
}
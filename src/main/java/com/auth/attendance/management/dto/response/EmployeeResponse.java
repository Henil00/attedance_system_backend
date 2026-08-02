package com.auth.attendance.management.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private int sr_no;

    private String employeeCode;

    private String name;

    private String phone;

    private String address;

    private LocalDate joiningDate;

    private BigDecimal presentSalary;

    private BigDecimal overtimeRatePerHour;

    private Integer workingHoursPerDay;

    private Boolean isActive;

    private LocalDateTime createdAt;
}
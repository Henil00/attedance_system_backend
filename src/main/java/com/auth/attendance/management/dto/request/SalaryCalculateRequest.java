package com.auth.attendance.management.dto.request;

import lombok.Data;

@Data
public class SalaryCalculateRequest {
    private Integer employeeSrNo;
    private Integer month;
    private Integer year;
}
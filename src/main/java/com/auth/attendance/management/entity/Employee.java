package com.auth.attendance.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sr_no")
    private int srNo;

    @Column(nullable = false, unique = true)
    private String employeeCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private String address;

    private LocalDate joiningDate;

    private BigDecimal presentSalary;

    private BigDecimal overtimeRatePerHour;

    private Integer workingHoursPerDay;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

package com.auth.attendance.management.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    @NotBlank(message = "Full name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Joining date is required")
    private LocalDate joiningDate;

    @NotNull(message = "Daily salary is required")
    @Positive(message = "Daily salary must be greater than zero")
    private BigDecimal presentSalary;

    @NotNull(message = "Overtime rate is required")
    @PositiveOrZero(message = "Overtime rate cannot be negative")
    private BigDecimal overtimeRatePerHour;

    @NotNull(message = "Working hours are required")
    @Min(1)
    @Max(24)
    private Integer workingHoursPerDay;
}

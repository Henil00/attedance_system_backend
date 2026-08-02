package com.auth.attendance.management.service;

import com.auth.attendance.management.dto.request.SalaryCalculateRequest;
import com.auth.attendance.management.dto.response.SalaryReportResponse;
import com.auth.attendance.management.entity.Attendance;
import com.auth.attendance.management.entity.Employee;
import com.auth.attendance.management.entity.SalaryReport;
import com.auth.attendance.management.enums.AttendanceStatus;
import com.auth.attendance.management.repository.AttendanceRepository;
import com.auth.attendance.management.repository.EmployeeRepository;
import com.auth.attendance.management.repository.SalaryReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {

    private final SalaryReportRepository salaryReportRepository;
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public SalaryReportResponse calculateSalary(SalaryCalculateRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeSrNo())
                .orElseThrow(() -> new RuntimeException("Employee not found with sr_no: " + request.getEmployeeSrNo()));

        YearMonth yearMonth = YearMonth.of(request.getYear(), request.getMonth());
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Attendance> attendanceList = attendanceRepository
                .findByEmployeeSrNoAndAttendanceDateBetween(request.getEmployeeSrNo(), startDate, endDate);

        int presentDays = 0;
        int halfDays = 0;
        int absentDays = 0;
        BigDecimal totalOvertime = BigDecimal.ZERO;

        for (Attendance att : attendanceList) {
            if (att.getStatus() == AttendanceStatus.PRESENT) {
                presentDays++;
            } else if (att.getStatus() == AttendanceStatus.HALF_DAY) {
                halfDays++;
            } else if (att.getStatus() == AttendanceStatus.ABSENT) {
                absentDays++;
            }

            if (att.getOvertimeHours() != null) {
                totalOvertime = totalOvertime.add(att.getOvertimeHours());
            }
        }

        BigDecimal presentSalary = employee.getPresentSalary();
        BigDecimal overtimeRate = employee.getOvertimeRatePerHour();

        BigDecimal presentAmount = presentSalary.multiply(BigDecimal.valueOf(presentDays));
        BigDecimal halfDayAmount = presentSalary.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(halfDays));
        BigDecimal overtimeAmount = overtimeRate.multiply(totalOvertime);

        BigDecimal totalSalary = presentAmount.add(halfDayAmount).add(overtimeAmount);

        // Check if report already exists for this employee/month/year
        SalaryReport report = salaryReportRepository
                .findByEmployeeSrNoAndMonthAndYear(request.getEmployeeSrNo(), request.getMonth(), request.getYear())
                .orElse(new SalaryReport());

        report.setEmployeeSrNo(employee.getSr_no());
        report.setEmployeeName(employee.getName());
        report.setMonth(request.getMonth());
        report.setYear(request.getYear());
        report.setPresentDays(presentDays);
        report.setHalfDays(halfDays);
        report.setAbsentDays(absentDays);
        report.setTotalOvertimeHours(totalOvertime);
        report.setPresentSalary(presentSalary);
        report.setOvertimeRatePerHour(overtimeRate);
        report.setHalfDayAmount(halfDayAmount);
        report.setOvertimeAmount(overtimeAmount);
        report.setTotalSalary(totalSalary);

        SalaryReport saved = salaryReportRepository.save(report);
        return toResponse(saved);
    }

    public List<SalaryReportResponse> getSalaryByEmployee(Integer employeeSrNo) {
        return salaryReportRepository.findByEmployeeSrNo(employeeSrNo)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SalaryReportResponse getSalaryReport(Integer srNo) {
        SalaryReport report = salaryReportRepository.findById(srNo)
                .orElseThrow(() -> new RuntimeException("Salary report not found with sr_no: " + srNo));
        return toResponse(report);
    }

    private SalaryReportResponse toResponse(SalaryReport report) {
        SalaryReportResponse response = new SalaryReportResponse();
        response.setSrNo(report.getSrNo());
        response.setEmployeeSrNo(report.getEmployeeSrNo());
        response.setEmployeeName(report.getEmployeeName());
        response.setMonth(report.getMonth());
        response.setYear(report.getYear());
        response.setPresentDays(report.getPresentDays());
        response.setHalfDays(report.getHalfDays());
        response.setAbsentDays(report.getAbsentDays());
        response.setTotalOvertimeHours(report.getTotalOvertimeHours());
        response.setPresentSalary(report.getPresentSalary());
        response.setOvertimeRatePerHour(report.getOvertimeRatePerHour());
        response.setHalfDayAmount(report.getHalfDayAmount());
        response.setOvertimeAmount(report.getOvertimeAmount());
        response.setTotalSalary(report.getTotalSalary());
        response.setCreatedAt(report.getCreatedAt());
        return response;
    }
}
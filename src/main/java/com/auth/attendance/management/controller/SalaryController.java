package com.auth.attendance.management.controller;

import com.auth.attendance.management.dto.request.SalaryCalculateRequest;
import com.auth.attendance.management.dto.response.SalaryReportResponse;
import com.auth.attendance.management.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping("/calculate")
    public ResponseEntity<SalaryReportResponse> calculateSalary(@RequestBody SalaryCalculateRequest request) {
        return new ResponseEntity<>(salaryService.calculateSalary(request), HttpStatus.CREATED);
    }

    @GetMapping("/{sr_no}")
    public ResponseEntity<SalaryReportResponse> getSalaryReport(@PathVariable("sr_no") Integer srNo) {
        return ResponseEntity.ok(salaryService.getSalaryReport(srNo));
    }

    @GetMapping("/employee/{employee_sr_no}")
    public ResponseEntity<List<SalaryReportResponse>> getSalaryByEmployee(
            @PathVariable("employee_sr_no") Integer employeeSrNo) {
        return ResponseEntity.ok(salaryService.getSalaryByEmployee(employeeSrNo));
    }
}
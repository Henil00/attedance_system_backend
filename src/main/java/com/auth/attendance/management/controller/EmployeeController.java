package com.auth.attendance.management.controller;

import com.auth.attendance.management.dto.request.EmployeeRequest;
import com.auth.attendance.management.dto.response.EmployeeResponse;
import com.auth.attendance.management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{sr_no}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable("sr_no") Integer srNo) {
        EmployeeResponse response = employeeService.getEmployeeById(srNo);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{sr_no}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable("sr_no") Integer srNo,
            @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.updateEmployee(srNo, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{sr_no}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable("sr_no") Integer srNo) {
        employeeService.deleteEmployee(srNo);
        return ResponseEntity.noContent().build();
    }
}
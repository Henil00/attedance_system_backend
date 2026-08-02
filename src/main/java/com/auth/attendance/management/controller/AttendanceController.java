package com.auth.attendance.management.controller;

import com.auth.attendance.management.dto.request.AttendanceRequest;
import com.auth.attendance.management.dto.response.AttendanceResponse;
import com.auth.attendance.management.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponse> markAttendance(@RequestBody AttendanceRequest request) {
        return new ResponseEntity<>(attendanceService.markAttendance(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @GetMapping("/{sr_no}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable("sr_no") Integer srNo) {
        return ResponseEntity.ok(attendanceService.getAttendanceById(srNo));
    }

    @GetMapping("/employee/{employee_sr_no}")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByEmployee(
            @PathVariable("employee_sr_no") Integer employeeSrNo) {
        return ResponseEntity.ok(attendanceService.getAttendanceByEmployee(employeeSrNo));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByDate(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(date));
    }

    @GetMapping("/range")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDateRange(startDate, endDate));
    }

    @GetMapping("/employee/{employee_sr_no}/range")
    public ResponseEntity<List<AttendanceResponse>> getEmployeeAttendanceByDateRange(
            @PathVariable("employee_sr_no") Integer employeeSrNo,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(attendanceService.getEmployeeAttendanceByDateRange(employeeSrNo, startDate, endDate));
    }

    @PutMapping("/{sr_no}")
    public ResponseEntity<AttendanceResponse> updateAttendance(
            @PathVariable("sr_no") Integer srNo,
            @RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.updateAttendance(srNo, request));
    }

    @DeleteMapping("/{sr_no}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable("sr_no") Integer srNo) {
        attendanceService.deleteAttendance(srNo);
        return ResponseEntity.noContent().build();
    }
}
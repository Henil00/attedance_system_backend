package com.auth.attendance.management.service;

import com.auth.attendance.management.dto.request.AttendanceRequest;
import com.auth.attendance.management.dto.response.AttendanceResponse;
import com.auth.attendance.management.entity.Attendance;
import com.auth.attendance.management.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceResponse markAttendance(AttendanceRequest request) {
        Attendance attendance = new Attendance();
        attendance.setEmployeeSrNo(request.getEmployeeSrNo());
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setStatus(request.getStatus());
        attendance.setOvertimeHours(request.getOvertimeHours() != null ? request.getOvertimeHours() : BigDecimal.ZERO);
        attendance.setNotes(request.getNotes());

        Attendance saved = attendanceRepository.save(attendance);
        return toResponse(saved);
    }

    public List<AttendanceResponse> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AttendanceResponse getAttendanceById(Integer srNo) {
        Attendance attendance = attendanceRepository.findById(srNo)
                .orElseThrow(() -> new RuntimeException("Attendance not found with sr_no: " + srNo));
        return toResponse(attendance);
    }

    public List<AttendanceResponse> getAttendanceByEmployee(Integer employeeSrNo) {
        return attendanceRepository.findByEmployeeSrNo(employeeSrNo)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponse> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponse> getAttendanceByDateRange(LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByAttendanceDateBetween(startDate, endDate)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponse> getEmployeeAttendanceByDateRange(Integer employeeSrNo, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeSrNoAndAttendanceDateBetween(employeeSrNo, startDate, endDate)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AttendanceResponse updateAttendance(Integer srNo, AttendanceRequest request) {
        Attendance attendance = attendanceRepository.findById(srNo)
                .orElseThrow(() -> new RuntimeException("Attendance not found with sr_no: " + srNo));

        attendance.setEmployeeSrNo(request.getEmployeeSrNo());
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setStatus(request.getStatus());
        attendance.setOvertimeHours(request.getOvertimeHours() != null ? request.getOvertimeHours() : BigDecimal.ZERO);
        attendance.setNotes(request.getNotes());

        Attendance updated = attendanceRepository.save(attendance);
        return toResponse(updated);
    }

    public void deleteAttendance(Integer srNo) {
        if (!attendanceRepository.existsById(srNo)) {
            throw new RuntimeException("Attendance not found with sr_no: " + srNo);
        }
        attendanceRepository.deleteById(srNo);
    }

    private AttendanceResponse toResponse(Attendance attendance) {
        AttendanceResponse response = new AttendanceResponse();
        response.setSrNo(attendance.getSrNo());
        response.setEmployeeSrNo(attendance.getEmployeeSrNo());
        response.setAttendanceDate(attendance.getAttendanceDate());
        response.setStatus(attendance.getStatus());
        response.setOvertimeHours(attendance.getOvertimeHours());
        response.setNotes(attendance.getNotes());
        response.setCreatedAt(attendance.getCreatedAt());
        return response;
    }
}
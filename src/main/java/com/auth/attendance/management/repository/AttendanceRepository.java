package com.auth.attendance.management.repository;

import com.auth.attendance.management.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByEmployeeSrNo(Integer employeeSrNo);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    Optional<Attendance> findByEmployeeSrNoAndAttendanceDate(Integer employeeSrNo, LocalDate attendanceDate);

    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    List<Attendance> findByEmployeeSrNoAndAttendanceDateBetween(Integer employeeSrNo, LocalDate startDate, LocalDate endDate);

    boolean existsByEmployeeSrNoAndAttendanceDate(Integer employeeSrNo, LocalDate attendanceDate);
}
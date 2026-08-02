package com.auth.attendance.management.repository;

import com.auth.attendance.management.entity.SalaryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryReportRepository extends JpaRepository<SalaryReport, Integer> {

    List<SalaryReport> findByEmployeeSrNo(Integer employeeSrNo);

    Optional<SalaryReport> findByEmployeeSrNoAndMonthAndYear(Integer employeeSrNo, Integer month, Integer year);
}
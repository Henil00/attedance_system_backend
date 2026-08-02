package com.auth.attendance.management.service;

import com.auth.attendance.management.dto.request.EmployeeRequest;
import com.auth.attendance.management.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest request);

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Integer srNo);

    EmployeeResponse updateEmployee(Integer srNo, EmployeeRequest request);

    void deleteEmployee(Integer srNo);
}
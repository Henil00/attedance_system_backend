package com.auth.attendance.management.mapper;

import com.auth.attendance.management.dto.request.EmployeeRequest;
import com.auth.attendance.management.dto.response.EmployeeResponse;
import com.auth.attendance.management.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request){
        Employee employee = new Employee();
        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setPresentSalary(request.getPresentSalary());
        employee.setOvertimeRatePerHour(request.getOvertimeRatePerHour());
        employee.setWorkingHoursPerDay(request.getWorkingHoursPerDay());
        employee.setIsActive(true);

        return employee;

    }

    public EmployeeResponse toResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setSr_no(employee.getSrNo());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setName(employee.getName());
        response.setPhone(employee.getPhone());
        response.setAddress(employee.getAddress());
        response.setJoiningDate(employee.getJoiningDate());
        response.setPresentSalary(employee.getPresentSalary());
        response.setOvertimeRatePerHour(employee.getOvertimeRatePerHour());
        response.setWorkingHoursPerDay(employee.getWorkingHoursPerDay());
        response.setIsActive(employee.getIsActive());
        response.setCreatedAt(employee.getCreatedAt());

        return response;
    }

    public void updateEntity(Employee employee, EmployeeRequest request) {

        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setPresentSalary(request.getPresentSalary());
        employee.setOvertimeRatePerHour(request.getOvertimeRatePerHour());
        employee.setWorkingHoursPerDay(request.getWorkingHoursPerDay());
    }
}

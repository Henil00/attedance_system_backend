package com.auth.attendance.management.service.impl;

import com.auth.attendance.management.dto.request.EmployeeRequest;
import com.auth.attendance.management.dto.response.EmployeeResponse;
import com.auth.attendance.management.entity.Employee;
import com.auth.attendance.management.exception.EmployeeAlreadyExistsException;
import com.auth.attendance.management.exception.EmployeeNotFoundException;
import com.auth.attendance.management.mapper.EmployeeMapper;
import com.auth.attendance.management.repository.EmployeeRepository;
import com.auth.attendance.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
            throw new EmployeeAlreadyExistsException(
                    "Employee with code '" + request.getEmployeeCode() + "' already exists"
            );
        }

        Employee employee = employeeMapper.toEntity(request);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Integer srNo) {
        Employee employee = employeeRepository.findById(srNo)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with sr_no: " + srNo
                ));
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Integer srNo, EmployeeRequest request) {
        Employee existingEmployee = employeeRepository.findById(srNo)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with sr_no: " + srNo
                ));

        // Check if employeeCode is being changed to one that already exists
        if (!existingEmployee.getEmployeeCode().equals(request.getEmployeeCode())
                && employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
            throw new EmployeeAlreadyExistsException(
                    "Employee with code '" + request.getEmployeeCode() + "' already exists"
            );
        }

        employeeMapper.updateEntity(existingEmployee, request);
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.toResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Integer srNo) {
        if (!employeeRepository.existsById(srNo)) {
            throw new EmployeeNotFoundException(
                    "Employee not found with sr_no: " + srNo
            );
        }
        employeeRepository.deleteById(srNo);
    }
}
package com.roman.controller.impl;

import com.roman.controller.EmployeeController;
import com.roman.dto.EmployeeBriefInfoDto;
import com.roman.dto.EmployeeDto;
import com.roman.dto.EmployeeFullInfoDto;
import com.roman.entity.Employee;
import com.roman.mappers.EmployeeMapper;
import com.roman.service.DepartmentService;
import com.roman.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final DepartmentService departmentService;

    @Override
    public EmployeeFullInfoDto create(EmployeeDto newEmployee) {
        Employee employee = employeeService.create(employeeMapper.toEntity(newEmployee, departmentService));
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeFullInfoDto update(@Valid EmployeeDto employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO, departmentService);
        employeeService.update(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeFullInfoDto findById(Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return employeeMapper.toDto(employee);
    }

    @Override
    public void dismissal(Long employeeId) {
        employeeService.dismissal(employeeId);
    }

    @Override
    public EmployeeFullInfoDto transferEmployee(Long employeeId, Long departmentId) {
        Employee employee = employeeService.findById(employeeId);
        employeeService.transferEmployee(employeeId, departmentId);
        return employeeMapper.toDto(employee);
    }

    @Override
    public void transferAllEmployees(Long sourceDepartmentId, Long targetDepartmentId) {
        employeeService.transferAllEmployee(sourceDepartmentId, targetDepartmentId);
    }

    @Override
    public EmployeeFullInfoDto findHeadOfEmployee(Long employeeId) {
        Employee employee = employeeService.getEmployeesHead(employeeId);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeFullInfoDto findEmployeeByEmail(String email) {
        Employee employee = employeeService.findByEmail(email);
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeBriefInfoDto> getEmployeesListByParam(boolean isHead) {
        List<Employee> employeeList = employeeService.getEmployeeListByParam(isHead);
        return employeeList.stream()
                .map(employeeMapper::toBriefDto)
                .collect(Collectors.toList());
    }
}

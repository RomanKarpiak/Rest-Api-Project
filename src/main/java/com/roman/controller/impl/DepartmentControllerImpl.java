package com.roman.controller.impl;

import com.roman.controller.DepartmentController;
import com.roman.dto.DepartmentDto;
import com.roman.dto.DepartmentInfoDto;
import com.roman.dto.EmployeeBriefInfoDto;
import com.roman.entity.Department;
import com.roman.entity.Employee;
import com.roman.mappers.DepartmentMapper;
import com.roman.mappers.EmployeeMapper;
import com.roman.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;


    @Override
    public DepartmentInfoDto findById(Long departmentId) {
        Department department = departmentService.findById(departmentId);
        return departmentMapper.toDto(department);
    }

    @Override
    public List<DepartmentInfoDto> findAll() {
        List<Department> departmentList = departmentService.findAll();
        return departmentList.stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentInfoDto findByDepartmentName(String departmentName) {
        Department department = departmentService.findByDepartmentName(departmentName);
        return departmentMapper.toDto(department);
    }

    @Override
    public DepartmentInfoDto create(DepartmentDto departmentDTO) {
        Department department = departmentService.create(departmentMapper.toEntity(departmentDTO, departmentService));
        return departmentMapper.toDto(department);
    }

    @Override
    public DepartmentDto update(@Valid DepartmentDto updatedDepartment) {
        departmentService.update(departmentMapper.toEntity(updatedDepartment, departmentService));
        return updatedDepartment;
    }

    @Override
    public void deleteById(Long departmentId) {
        departmentService.delete(departmentId);
    }

    @Override
    public Set<DepartmentInfoDto> findAllSubDepartments(Long departmentId) {
        Department department = departmentService.findById(departmentId);
        Set<Department> subDepartments = departmentService.findAllSubDepartments(department);
        return subDepartments.stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<DepartmentInfoDto> findSubDepartments(Long departmentId) {
        Department department = departmentService.findById(departmentId);
        Set<Department> subDepartments = department.getSubDepartments();
        return subDepartments.stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toSet());

    }

    @Override
    public DepartmentInfoDto transferDepartment(Long subDepartmentId, Long headDepartmentId) {
        Department subDepartment = departmentService.findById(subDepartmentId);
        Department headDepartment = departmentService.findById(headDepartmentId);
        subDepartment.setMainDepartment(headDepartment);
        departmentService.update(subDepartment);
        return departmentMapper.toDto(subDepartment);
    }

    @Override
    public Set<DepartmentInfoDto> findAllHeadDepartments(Long departmentId) {
        Department department = departmentService.findById(departmentId);
        Set<Department> headDepartments = departmentService.findAllHeadDepartments(department);
        return headDepartments.stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Long getSalaryFund(Long departmentId) {
        return departmentService.getSalaryFund(departmentId);
    }

    @Override
    public List<EmployeeBriefInfoDto> getEmployeeList(Long departmentId) {
        List<Employee> employees = departmentService.getEmployeeList(departmentId);
        return employees.stream()
                .map(employeeMapper::toBriefDto)
                .collect(Collectors.toList());
    }

}

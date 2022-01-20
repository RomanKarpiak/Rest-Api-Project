package com.roman.controller;

import com.roman.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RequestMapping(value = "/departments")
public interface DepartmentController {

    @PostMapping
    @Validated(value = OnCreate.class)
    DepartmentInfoDto create(@Valid @RequestBody DepartmentDto departmentDTO);

    @PutMapping
    @Validated(value = OnUpdate.class)
    DepartmentDto update(@Valid @RequestBody DepartmentDto newDepartment);

    @DeleteMapping("/{departmentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long departmentId);

    @GetMapping
    List<DepartmentInfoDto> findAll();

    @GetMapping(value = "/{departmentId}")
    DepartmentInfoDto findById(@PathVariable Long departmentId);

    @GetMapping(value = "/{departmentId}/sub-departments")
    Set<DepartmentInfoDto> findSubDepartments(@PathVariable Long departmentId);

    @GetMapping(value = "/{departmentId}/all-sub-departments")
    Set<DepartmentInfoDto> findAllSubDepartments(@PathVariable Long departmentId);

    @GetMapping(value = "/name/{departmentName}")
    DepartmentInfoDto findByDepartmentName(@PathVariable String departmentName);

    @PutMapping(value = "/{subDepartmentId}/transfer/{headDepartmentId}")
    DepartmentInfoDto transferDepartment(@PathVariable Long subDepartmentId, @PathVariable Long headDepartmentId);

    @GetMapping(value = "/{departmentId}/all-head-departments")
    Set<DepartmentInfoDto> findAllHeadDepartments(@PathVariable Long departmentId);

    @GetMapping(value = "/{departmentId}/salary-fund")
    Long getSalaryFund(@PathVariable Long departmentId);

    @GetMapping(value = "/{departmentId}/employee-list")
    List<EmployeeBriefInfoDto> getEmployeeList(@PathVariable Long departmentId);

}

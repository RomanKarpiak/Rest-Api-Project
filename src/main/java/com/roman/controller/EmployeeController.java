package com.roman.controller;

import com.roman.dto.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping(value = "/employees")
public interface EmployeeController {

    @PostMapping
    @Validated(value = OnCreate.class)
    EmployeeFullInfoDto create(@Valid @RequestBody EmployeeDto employee);

    @PutMapping
    @Validated(value = OnUpdate.class)
    EmployeeFullInfoDto update(@Valid @RequestBody EmployeeDto employeeDTO);

    @GetMapping(value = "/{employeeId}")
    EmployeeFullInfoDto findById(@PathVariable Long employeeId);

    @DeleteMapping(value = "/{employeeId}")
    void dismissal(@PathVariable Long employeeId);

    @PutMapping(value = "/{employeeId}/transfer/{departmentId}")
    EmployeeFullInfoDto transferEmployee(@PathVariable Long employeeId, @PathVariable Long departmentId);

    @PutMapping(value = "/{sourceDepartmentId}/transfer-all/{targetDepartmentId}")
    void transferAllEmployees(@PathVariable Long sourceDepartmentId, @PathVariable Long targetDepartmentId);

    @GetMapping(value = "/{employeeId}/head")
    EmployeeFullInfoDto findHeadOfEmployee(@PathVariable Long employeeId);

    @GetMapping(value = "/find")
    EmployeeFullInfoDto findEmployeeByEmail(@RequestParam String email);

    @GetMapping(value = "/filter")
    List<EmployeeBriefInfoDto> getEmployeesListByParam(@RequestParam boolean isHead);

}

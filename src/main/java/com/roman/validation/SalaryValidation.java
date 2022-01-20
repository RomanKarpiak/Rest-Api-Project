package com.roman.validation;

import com.roman.dto.EmployeeDto;
import com.roman.entity.Department;
import com.roman.repo.EmployeeRepo;
import com.roman.service.DepartmentService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalaryValidation implements ConstraintValidator<Salary, EmployeeDto> {

    private final DepartmentService service;
    private final EmployeeRepo employeeRepo;


    public SalaryValidation(DepartmentService service, EmployeeRepo employeeRepo) {
        this.service = service;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void initialize(Salary constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EmployeeDto employee, ConstraintValidatorContext constraintValidatorContext) {
        Department department = service.findById(employee.getDepartmentId());
        if (department.getEmployees().isEmpty()) {
            return true;
        } else if (!employee.isHeadOfDepartment()) {
            return (getSalaryOfHeadOfDepartment(employee) - employee.getSalary()) > 0;
        } else {
            return (employee.getSalary() - getMaxSalaryOfEmployeesOfDepartment(employee)) > 0;
        }
    }

    private Long getSalaryOfHeadOfDepartment(EmployeeDto employee) {
        Department department = service.findById(employee.getDepartmentId());
        return employeeRepo.getSalaryOfHeadOfDepartment(department);
    }

    private Long getMaxSalaryOfEmployeesOfDepartment(EmployeeDto employee) {
        Department department = service.findById(employee.getDepartmentId());
        return employeeRepo.getMaxSalaryOfEmployeesOfDepartment(department);

    }
}

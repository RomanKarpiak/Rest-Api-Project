package com.roman.validation;

import com.roman.dto.EmployeeDto;
import com.roman.entity.Department;
import com.roman.entity.Employee;
import com.roman.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HeadOfDepartmentValidation implements ConstraintValidator<HeadOfDepartment, EmployeeDto> {

    private final DepartmentRepo depRepo;

    @Autowired
    public HeadOfDepartmentValidation(DepartmentRepo depRepo) {
        this.depRepo = depRepo;
    }


    @Override
    public void initialize(HeadOfDepartment constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EmployeeDto dto, ConstraintValidatorContext context) {
        Long id = dto.getDepartmentId();
        Department department = depRepo.getById(id);
        Employee employee = department.getEmployees()
                .stream()
                .filter(Employee::isHeadOfDepartment)
                .findFirst()
                .orElse(null);
        if (employee == null) {
            return true;
        } else {
            return !(dto.isHeadOfDepartment() && employee.isHeadOfDepartment());
        }
    }

}

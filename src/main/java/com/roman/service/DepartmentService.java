package com.roman.service;

import com.roman.entity.Department;
import com.roman.entity.Employee;

import java.util.List;
import java.util.Set;

public interface DepartmentService {

    Department findById(Long id);

    Department findByDepartmentName(String departmentName);

    List<Department> findAll();

    Set<Department> findAllSubDepartments(Department department);

    Set<Department> findAllHeadDepartments(Department department);

    Department create(Department department);

    void update(Department department);

    void delete(Long departmentId);

    Long getSalaryFund(Long id);

    List<Employee> getEmployeeList(Long id);


}

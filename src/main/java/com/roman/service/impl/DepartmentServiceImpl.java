package com.roman.service.impl;

import com.roman.entity.Department;
import com.roman.entity.Employee;
import com.roman.exceptions.DepartmentAlreadyExistsException;
import com.roman.exceptions.DepartmentHasSubDepartmentException;
import com.roman.exceptions.DepartmentNotEmptyException;
import com.roman.exceptions.DepartmentNotFoundException;
import com.roman.repo.DepartmentRepo;
import com.roman.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public Department findByDepartmentName(String departmentName) {
        return Optional.ofNullable(departmentRepo.findByDepartmentName(departmentName))
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName));
    }

    @Override
    public void delete(Long departmentId) {
        Department department = findById(departmentId);
        if (department.getEmployees().isEmpty() && department.getSubDepartments().isEmpty()) {
            departmentRepo.deleteById(departmentId);
        } else if (!department.getEmployees().isEmpty()) {
            throw new DepartmentNotEmptyException(departmentId);
        } else if (!department.getSubDepartments().isEmpty()) {
            throw new DepartmentHasSubDepartmentException(departmentId);
        }
    }

    @Override
    public Department create(Department department) {
        String departmentName = department.getDepartmentName();
        if (departmentRepo.findByDepartmentName(departmentName) == null) {
            return departmentRepo.save(department);
        } else {
            throw new DepartmentAlreadyExistsException(departmentName);
        }
    }

    @Override
    public void update(Department newDepartment) {
        departmentRepo.save(newDepartment);
    }

    @Override
    public Set<Department> findAllSubDepartments(Department department) {
        Set<Department> subDepartments = new LinkedHashSet<>();
        Set<Department> tempContainer;
        Queue<Department> queue = new LinkedList<>();
        queue.add(department);
        while (!queue.isEmpty()) {
            Department currentDepartment = queue.poll();
            tempContainer = currentDepartment.getSubDepartments();
            subDepartments.addAll(tempContainer);
            queue.addAll(tempContainer);
        }
        return subDepartments;
    }

    @Override
    public Set<Department> findAllHeadDepartments(Department department) {
        Set<Department> headDepartments = new LinkedHashSet<>();
        Department tempContainer;
        Deque<Department> queue = new ArrayDeque<>();
        queue.add(department);
        while (!queue.isEmpty()) {
            Department currentDepartment = queue.pop();
            tempContainer = currentDepartment.getMainDepartment();
            if (tempContainer == null) {
                break;
            }
            headDepartments.add(tempContainer);
            queue.add(tempContainer);
        }
        return headDepartments;
    }

    @Override
    public Long getSalaryFund(Long id) {
        Department department = findById(id);
        List<Employee> employees = department.getEmployees();
        return employees.stream()
                .mapToLong(Employee::getSalary)
                .sum();
    }

    @Override
    public List<Employee> getEmployeeList(Long id) {
        Department department = findById(id);
        return department.getEmployees();
    }
}

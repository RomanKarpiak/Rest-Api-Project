package com.roman.service.impl;

import com.roman.entity.Department;
import com.roman.entity.Employee;
import com.roman.exceptions.EmployeeAlreadyExistsException;
import com.roman.exceptions.EmployeeNotFoundException;
import com.roman.exceptions.TwoHeadOfDepartmentException;
import com.roman.repo.EmployeeRepo;
import com.roman.service.DepartmentService;
import com.roman.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final DepartmentService departmentService;

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee create(Employee employee) {
        List<Employee> employees = employeeRepo.findAll();
        if (!employees.contains(employee)) {
            return employeeRepo.save(employee);
        } else {
            throw new EmployeeAlreadyExistsException();
        }
    }

    @Override
    public void update(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public Employee findById(Long employeeId) {
        return employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Override
    public void dismissal(Long employeeId) {
        Employee employee = findById(employeeId);
        employee.setDepartment(null);
        employee.setDateOfDismissal(LocalDate.now());
        employeeRepo.save(employee);
    }

    @Override
    public void transferEmployee(Long employeeId, Long departmentId) {
        Employee employee = findById(employeeId);
        Department department = departmentService.findById(departmentId);
        Employee headOfDepartment = department.getEmployees().stream()
                .filter(Employee::isHeadOfDepartment)
                .findFirst()
                .orElse(null);
        if (headOfDepartment != null && employee.isHeadOfDepartment()) {
            throw new TwoHeadOfDepartmentException();
        } else {
            employee.setDepartment(department);
            employeeRepo.save(employee);
        }
    }

    @Override
    @Transactional
    public void transferAllEmployee(Long sourceDepartmentId, Long targetDepartmentId) {
        Department sourceDepartment = departmentService.findById(sourceDepartmentId);
        sourceDepartment.getEmployees()
                .forEach(employee -> transferEmployee(employee.getId(), targetDepartmentId));
    }

    @Override
    public Employee getEmployeesHead(Long employeeId) {
        Employee employee = findById(employeeId);
        if (employee.getDepartment().getMainDepartment() == null) {
            return employee;
        }
        Department department;
        if (employee.isHeadOfDepartment()) {
            department = employee.getDepartment().getMainDepartment();
        } else {
            department = employee.getDepartment();
        }
        return getHeadOfDepartment(department);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    @Override
    public List<Employee> getEmployeeListByParam(boolean isHead) {
        return employeeRepo.getEmployeeListByParam(isHead);
    }

    private Employee getHeadOfDepartment(Department department) {
        return department.getEmployees()
                .stream()
                .filter(Employee::isHeadOfDepartment)
                .findFirst()
                .orElse(null);
    }
}

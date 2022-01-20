package com.roman.repo;

import com.roman.entity.Department;
import com.roman.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);

    @Query("SELECT max(e.salary) FROM Employee e WHERE e.department = :department AND e.isHeadOfDepartment = 'false'")
    Long getMaxSalaryOfEmployeesOfDepartment(@Param("department") Department department);

    @Query("SELECT max(e.salary) FROM Employee e WHERE e.department = :department")
    Long getSalaryOfHeadOfDepartment(@Param("department") Department department);

    @Query("SELECT e FROM Employee e WHERE e.isHeadOfDepartment = :isHead")
    List<Employee> getEmployeeListByParam(@Param("isHead") boolean isHead);

}

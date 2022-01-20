package com.roman.mappers;

import com.roman.dto.EmployeeBriefInfoDto;
import com.roman.dto.EmployeeDto;
import com.roman.dto.EmployeeFullInfoDto;
import com.roman.entity.Employee;
import com.roman.service.DepartmentService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    @Mapping(target = "departmentId", source = "employee", qualifiedByName = "getDepartmentId")
    EmployeeFullInfoDto toDto(Employee employee);

    EmployeeBriefInfoDto toBriefDto(Employee employee);

    @Mapping(target = "department", expression = "java(service.findById(employee.getDepartmentId()))")
    Employee toEntity(EmployeeDto employee, @Context DepartmentService service);

    @Named("getDepartmentId")
    default Long getDepartmentId(Employee employee) {
        if (employee.getDepartment() == null) {
            return null;
        } else {
            return employee.getDepartment().getId();
        }
    }
}

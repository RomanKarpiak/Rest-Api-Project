package com.roman.mappers;

import com.roman.dto.EmployeeFullInfoDto;
import com.roman.entity.Department;
import com.roman.entity.Employee;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeQualifier {

    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeQualifier(EmployeeMapper mapper) {
        this.mapper = mapper;
    }

    @Named("getHeadOfDepartment")
    public EmployeeFullInfoDto getHeadOfDepartment(Department departmentObject) {
        if (departmentObject.getEmployees() == null) {
            return null;
        } else {
            Employee headOfDepartment = departmentObject.getEmployees()
                    .stream()
                    .filter(Employee::isHeadOfDepartment)
                    .findFirst()
                    .orElse(null);
            return mapper.toDto(headOfDepartment);
        }
    }

}

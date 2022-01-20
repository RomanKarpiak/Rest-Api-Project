package com.roman.mappers;

import com.roman.dto.DepartmentDto;
import com.roman.dto.DepartmentInfoDto;
import com.roman.entity.Department;
import com.roman.service.DepartmentService;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-04T13:58:42+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Autowired
    private EmployeeQualifier employeeQualifier;

    @Override
    public DepartmentInfoDto toDto(Department departmentObject) {
        if ( departmentObject == null ) {
            return null;
        }

        DepartmentInfoDto departmentInfoDto = new DepartmentInfoDto();

        departmentInfoDto.setId( departmentObject.getId() );
        departmentInfoDto.setDepartmentName( departmentObject.getDepartmentName() );
        departmentInfoDto.setCreationDate( departmentObject.getCreationDate() );
        departmentInfoDto.setHeadOfDepartment( employeeQualifier.getHeadOfDepartment( departmentObject ) );
        departmentInfoDto.setQuantityOfEmployees( getQuantityOfEmployees( departmentObject ) );

        return departmentInfoDto;
    }

    @Override
    public Department toEntity(DepartmentDto departmentDTO, DepartmentService service) {
        if ( departmentDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentDTO.getId() );
        department.setDepartmentName( departmentDTO.getDepartmentName() );
        department.setCreationDate( departmentDTO.getCreationDate() );
        department.setMainDepartment( getMainDepartment( departmentDTO, service ) );

        return department;
    }
}

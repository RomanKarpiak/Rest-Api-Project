package com.roman.mappers;

import com.roman.dto.DepartmentDto;
import com.roman.dto.DepartmentInfoDto;
import com.roman.entity.Department;
import com.roman.service.DepartmentService;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = EmployeeQualifier.class)
public interface DepartmentMapper {


    @Mapping(target = "id", source = "departmentObject.id")
    @Mapping(target = "departmentName", source = "departmentObject.departmentName")
    @Mapping(target = "creationDate", source = "departmentObject.creationDate")
    @Mapping(target = "headOfDepartment", source = "departmentObject", qualifiedByName = "getHeadOfDepartment")
    @Mapping(target = "quantityOfEmployees", source = "departmentObject", qualifiedByName = "quantityOfEmployees")
    DepartmentInfoDto toDto(Department departmentObject);


    @Mapping(target = "id", source = "departmentDTO.id")
    @Mapping(target = "departmentName", source = "departmentDTO.departmentName")
    @Mapping(target = "creationDate", source = "departmentDTO.creationDate")
    @Mapping(target = "mainDepartment", source = "departmentDTO", qualifiedByName = "getMainDepartment")
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "subDepartments", ignore = true)
    Department toEntity(DepartmentDto departmentDTO, @Context DepartmentService service);

    @Named("quantityOfEmployees")
    default int getQuantityOfEmployees(Department departmentObject) {
        return departmentObject.getEmployees() == null ? 0 : departmentObject.getEmployees().size();
    }

    @Named("getMainDepartment")
    default Department getMainDepartment(DepartmentDto departmentDTO, @Context DepartmentService service) {
        return departmentDTO.getMainDepartment() == null ? null : service.findById(departmentDTO.getMainDepartment());
    }
}

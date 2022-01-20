package com.roman.mappers;

import com.roman.dto.EmployeeBriefInfoDto;
import com.roman.dto.EmployeeDto;
import com.roman.dto.EmployeeFullInfoDto;
import com.roman.entity.Employee;
import com.roman.service.DepartmentService;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-01T21:37:22+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeFullInfoDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeFullInfoDto employeeFullInfoDto = new EmployeeFullInfoDto();

        employeeFullInfoDto.setDepartmentId( getDepartmentId( employee ) );
        employeeFullInfoDto.setId( employee.getId() );
        employeeFullInfoDto.setLastName( employee.getLastName() );
        employeeFullInfoDto.setFirstName( employee.getFirstName() );
        employeeFullInfoDto.setPatronymic( employee.getPatronymic() );
        employeeFullInfoDto.setGender( employee.getGender() );
        employeeFullInfoDto.setBirthday( employee.getBirthday() );
        employeeFullInfoDto.setPhone( employee.getPhone() );
        employeeFullInfoDto.setEmail( employee.getEmail() );
        employeeFullInfoDto.setDateOfAdmission( employee.getDateOfAdmission() );
        employeeFullInfoDto.setDateOfDismissal( employee.getDateOfDismissal() );
        employeeFullInfoDto.setPosition( employee.getPosition() );
        employeeFullInfoDto.setSalary( employee.getSalary() );
        employeeFullInfoDto.setHeadOfDepartment( employee.isHeadOfDepartment() );

        return employeeFullInfoDto;
    }

    @Override
    public EmployeeBriefInfoDto toBriefDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeBriefInfoDto employeeBriefInfoDto = new EmployeeBriefInfoDto();

        employeeBriefInfoDto.setId( employee.getId() );
        employeeBriefInfoDto.setLastName( employee.getLastName() );
        employeeBriefInfoDto.setFirstName( employee.getFirstName() );
        employeeBriefInfoDto.setPosition( employee.getPosition() );

        return employeeBriefInfoDto;
    }

    @Override
    public Employee toEntity(EmployeeDto employee, DepartmentService service) {
        if ( employee == null ) {
            return null;
        }

        Employee employee1 = new Employee();

        employee1.setId( employee.getId() );
        employee1.setLastName( employee.getLastName() );
        employee1.setFirstName( employee.getFirstName() );
        employee1.setPatronymic( employee.getPatronymic() );
        employee1.setGender( employee.getGender() );
        employee1.setBirthday( employee.getBirthday() );
        employee1.setPhone( employee.getPhone() );
        employee1.setEmail( employee.getEmail() );
        employee1.setDateOfAdmission( employee.getDateOfAdmission() );
        employee1.setDateOfDismissal( employee.getDateOfDismissal() );
        employee1.setPosition( employee.getPosition() );
        employee1.setSalary( employee.getSalary() );
        employee1.setHeadOfDepartment( employee.isHeadOfDepartment() );

        employee1.setDepartment( service.findById(employee.getDepartmentId()) );

        return employee1;
    }
}

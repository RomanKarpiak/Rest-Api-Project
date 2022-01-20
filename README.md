REST-API-Project

Pre Requesting

1.JDK 1.8

2.IDE (Intellij idea)

3.Maven

4.Spring boot 2.5.0

5.Hibernate 5.2.18.Final

6.Spring boot 2.5.0

7.spring-boot-starter-data-jpa

8.spring-boot-starter-web

9.spring-boot-starter-validation

10.spring-boot-starter-test

11.spring-boot-actuator

12.Liquibase

12.PostgreSQL 42.2.19 (on-diskdatabase ) prod profile

14.Lombok 1.8.18

15.Mapstruct 1.4.2.Final

16.Jackson-datatype-joda 2.12.13

17.Commons-validator 1.7

18.Maven-compiler-plugin 3.8.1

19.SonarLint

Rest service

DepartmentController:

Api url: http://localhost:8080/departments

Http Method :GET

List of departments

Api url: http://localhost:8080/departments

Http Method :PUT

Update department

Api url: http://localhost:8080/departments

Http Method :POST

Create department

Api url: http://localhost:8080/departments/{departmentId}

Http Method :DELETE

Delete department

Api url: http://localhost:8080/departments/{departmentId}

Http Method :GET

Department with department.id = {departmentId}

API = http://localhost:8080/departments/{departmentId}/sub-departments

Http Method :GET

List of subDepartments(at a lower level)

Api url: http://localhost:8080/departments/{departmentId}/all-sub-departments

Http Method :GET

List of all subDepartments

Api url: http://localhost:8080/departments/name/{departmentName}

Http Method :GET

Department with name = {departmentName}

Api url: http://localhost:8080/departments/{subDepartmentId}/transfer/{headDepartmentId}

Http Method :GET

Changes the sub-department's main department

Api url: http://localhost:8080/departments/{departmentId}/all-head-departments

Http Method :GET

List of all the higher-level departments of this department.

Api url: http://localhost:8080/departments/{departmentId}/salary-fund

Http Method :GET

The salary fund of this department

Api url: http://localhost:8080/departments/{departmentId}/employee-list

Http Method :GET

List of employees of this department

EmployeeController

Api url: http://localhost:8080/employees

Http Method :GET

List of employees

Api url: http://localhost:8080/employees

Http Method :POST

Create employee

Api url: http://localhost:8080/employees

Http Method :PUT

Update employee

Api url: http://localhost:8080/employees/{id}

Http Method :DELETE

Delete employees employees.id={id}

Api url: http://localhost:8080/employees/{id}/transfer/{departmentId}

Http Method :GET

Transfer employee to another department

Api url: http://localhost:8080/employees/{id}/transfer-a/{departmentId}

Http Method :GET

Transfer all employees from one department  to another department

Api url: http://localhost:8080/employees/{id}/head

Http Method :GET

Getting information about the head of this employee.

Api url: http://localhost:8080/employees/{id}/head

Http Method :GET

Getting information about the head of this employee.

Api url: http://localhost:8080/employees/find?email=adress@gmail.com

Http Method :GET

Find employee by email

Api url: http://localhost:8080/employees/filter?isHead=true

Http Method :GET

List of heads if isHead=true

List of employee who is not head if isHead=false

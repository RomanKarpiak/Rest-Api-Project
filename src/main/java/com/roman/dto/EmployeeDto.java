package com.roman.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roman.entity.Gender;
import com.roman.validation.*;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@HeadOfDepartment
@AdmissionDate
@DismissalDate
@Salary
@Data
public class EmployeeDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    @JsonProperty("id")
    private Long id;


    @NotBlank(message = "Please enter the LastName")
    @Size(min = 2, max = 30, message = "The Last name should be between 2 and 30 characters")
    @Pattern(regexp = "[А-Яа-яЁё-]*", message = "You can use the following characters: [А-Яа-яЁё-]*")
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank(message = "Please enter the FirstName")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    @Pattern(regexp = "[А-Яа-яЁё-]*", message = "You can use the following characters: [А-Яа-яЁё-]*")
    @JsonProperty("firstName")
    private String firstName;


    @Pattern(regexp = "[А-Яа-яЁё-]*", message = "You can use the following characters: [А-Яа-яЁё-]*")
    @Size(min = 2, message = "Patronymic should be between 2 and 30 characters")
    @JsonProperty("patronymic")
    private String patronymic;


    @JsonProperty(value = "gender")
    @Enumerated(EnumType.STRING)
    @EnumValidator(acceptedValues = {"male", "female"},
            message = "Invalid dataType",groups = {OnCreate.class,OnUpdate.class})
    private Gender gender;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birthday")
    private LocalDate birthday;


    @Pattern(regexp = "^\\+?[78][-(]?\\d{3}\\)?[ -]?\\d{3}[ -]?\\d{2}[ -]?\\d{2}$", message = "The Phone number is not valid")
    @NotBlank
    @JsonProperty("phone")
    private String phone;

    @NotBlank
    @Email(message = "Email should be valid", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @JsonProperty("email")
    private String email;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("dateOfAdmission")
    private LocalDate dateOfAdmission;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("dateOfDismissal")
    private LocalDate dateOfDismissal;

    @NotBlank
    @JsonProperty("position")
    @Size(min = 2, message = "Position should be between 2 and 30 characters")
    private String position;

    @JsonProperty("salary")
    @Positive
    private Long salary;

    @NotNull
    @JsonProperty("isHeadOfDepartment")
    private boolean isHeadOfDepartment;

    @JsonProperty("departmentId")
    @Positive
    private Long departmentId;
}

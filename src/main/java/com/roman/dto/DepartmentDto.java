package com.roman.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "departmentName")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "DepartmentName should be between 2 and 30 characters")
    private String departmentName;

    @JsonProperty(value = "creationDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate creationDate;

    @JsonProperty(value = "mainDepartment")
    private Long mainDepartment;

}

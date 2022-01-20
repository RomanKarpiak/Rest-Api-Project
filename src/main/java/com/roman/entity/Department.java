package com.roman.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "departments")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "creation_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employees;

    @OneToMany(mappedBy = "mainDepartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Department> subDepartments = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "main_department_id")
    private Department mainDepartment;
}

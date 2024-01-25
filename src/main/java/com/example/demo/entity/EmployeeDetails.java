package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nationality;
    private String gender;
    
    @OneToOne(mappedBy = "employeeDetails")
    private Employee employee;
}

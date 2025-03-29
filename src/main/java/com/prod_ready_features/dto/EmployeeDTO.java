package com.prod_ready_features.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import jakarta.validation.constraints.*;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private Long id ;


    private String name;


    private String email;


    private Integer age;


    private String roll; //ADMIN|USER


    private Double salary;
    private LocalDate dateOfJoining;

    private Boolean isActive;


    
    }
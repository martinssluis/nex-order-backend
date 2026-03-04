package com.aceleradev.backend.commons.dto;

import com.aceleradev.backend.commons.enums.Role;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {

    public String name;
    public String password;
    public Boolean isActive;
    public String phoneNumber;
    public String email;
    public String description;
    public Instant lastLogin;
    private Role role;
    private Double baseSalary;

}

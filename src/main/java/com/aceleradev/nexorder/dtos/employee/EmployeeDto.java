package com.aceleradev.nexorder.dtos.employee;

import com.aceleradev.nexorder.commons.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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

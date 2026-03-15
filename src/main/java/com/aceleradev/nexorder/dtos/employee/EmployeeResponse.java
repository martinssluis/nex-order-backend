package com.aceleradev.nexorder.dtos.employee;

import com.aceleradev.nexorder.commons.enums.Role;

import java.time.Instant;
import java.time.LocalDate;

public record EmployeeResponse (
        Long id,
        String name,
        Boolean active,
        String phoneNumber,
        String email,
        String identifier,
        String description,
        LocalDate createdAt,
        Instant lastLogin,
        Role role,
        Double baseSalary
){

}

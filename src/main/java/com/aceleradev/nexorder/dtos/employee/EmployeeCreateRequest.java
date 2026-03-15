package com.aceleradev.nexorder.dtos.employee;

import com.aceleradev.nexorder.commons.enums.Role;

public record EmployeeCreateRequest(
        String name,
        String password,
        Boolean active,
        String phoneNumber,
        String email,
        String description,
        String identifier,
        Double baseSalary,
        Role role
){

}
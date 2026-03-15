package com.aceleradev.nexorder.dtos.employee;

import com.aceleradev.nexorder.commons.enums.Role;

public record EmployeeUpdateRequest(
        String name,
        Boolean active,
        String phoneNumber,
        String email,
        String description,
        String identifier,
        Double baseSalary,
        Role role
) {

}

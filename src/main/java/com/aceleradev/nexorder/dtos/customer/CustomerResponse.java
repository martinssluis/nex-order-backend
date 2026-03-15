package com.aceleradev.nexorder.dtos.customer;

import java.time.Instant;
import java.time.LocalDate;

public record CustomerResponse(
        Long id,
        String name,
        Boolean active,
        String phoneNumber,
        String email,
        String description,
        String identifier,
        LocalDate createdAt,
        Instant lastLogin
) {

}

package com.aceleradev.nexorder.dtos.customer;

public record CustomerUpdateRequest(
        String name,
        Boolean active,
        String phoneNumber,
        String email,
        String description
) {

}

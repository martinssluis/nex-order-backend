package com.aceleradev.nexorder.dtos.customer;

public record CustomerCreateRequest (
        String name,
        String password,
        Boolean active,
        String phoneNumber,
        String email,
        String description,
        String identifier
){

}
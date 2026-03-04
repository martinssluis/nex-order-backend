package com.aceleradev.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_costumer")
public class Customer extends User {

    public Customer(){
        super();
    }

    public Customer(
            Long id,
            String name,
            String password,
            Boolean isActive,
            String phoneNumber,
            String email,
            String identifier,
            String description,
            LocalDate createdAt,
            LocalDate lastLogin
    ){
        super(id, name, password, isActive, phoneNumber, email, identifier, description, createdAt, null);
    }

}

package com.aceleradev.nexorder.entities;

import com.aceleradev.nexorder.commons.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tb_employee")
public class Employee extends User {
    @Enumerated(EnumType.STRING)
    private Role role;
    private Double baseSalary;

    public Employee(){}

    public Employee(
            Long id,
            String name,
            String password,
            Boolean active,
            String phoneNumber,
            String email,
            String identifier,
            String description,
            Instant lastLogin,
            Role role,
            Double baseSalary) {

        super(id, name, password, active, phoneNumber, email, identifier,
                 description, lastLogin);
        setRole(role);
        this.baseSalary = baseSalary;
    }
}

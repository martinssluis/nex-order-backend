package com.aceleradev.nexorder.entities;

import com.aceleradev.nexorder.commons.enums.Role;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_employee")
public class Employee extends User {
    @Enumerated(EnumType.STRING)
    private Role role;
    private Double baseSalary;


    public Employee(){}

    public Employee(Long id, String name, String password, Boolean isActive,
                    String phoneNumber, String email, String identifier,
                    String description, LocalDate createdAt,
                    Instant lastLogin, Role role, Double baseSalary) {

        super(id, name, password, isActive, phoneNumber, email, identifier,
                 description, createdAt, lastLogin);
        setRole(role);
        this.baseSalary = baseSalary;

    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

}

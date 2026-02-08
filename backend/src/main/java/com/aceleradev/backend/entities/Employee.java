package com.aceleradev.backend.entities;

import com.aceleradev.backend.commons.enums.Role;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_employee")
public class Employee extends User {
    private Integer role;
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

        return Role.valueOf(role);
    }

    public void setRole(Role role) {
        if (role != null){
        this.role = role.getCode();
        }

    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

}

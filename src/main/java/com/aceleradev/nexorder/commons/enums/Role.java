package com.aceleradev.nexorder.commons.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    SELLER("Vendedor"),
    MANAGER("Gestor"),
    INTERN("Estagiário");

    @JsonValue
    private final String name;

}

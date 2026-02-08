package com.aceleradev.backend.commons.enums;

public enum Role {
    SELLER(1),
    MANAGER(2),
    INTERN(3);

    private int code;

    private Role(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Role valueOf(int code){
        for (Role value : Role.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Role code");
    }
}
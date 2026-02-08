package com.aceleradev.backend.commons.enums;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    CANCELLED(3);

    private Integer code;

    private OrderStatus(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(Integer code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}

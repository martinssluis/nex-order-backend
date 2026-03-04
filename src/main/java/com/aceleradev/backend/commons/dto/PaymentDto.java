package com.aceleradev.backend.commons.dto;

import com.aceleradev.backend.commons.enums.PaymentMethod;

public class PaymentDto {
    private Double totalValue;
    private PaymentMethod paymentMethod;

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}

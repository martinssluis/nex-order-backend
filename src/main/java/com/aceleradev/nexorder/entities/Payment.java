package com.aceleradev.nexorder.entities;

import com.aceleradev.nexorder.commons.enums.PaymentMethod;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "tb_payment")
public class Payment {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private ZonedDateTime moment;
    private double amountPaid;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToOne
    @JoinColumn(name="orderId")
    private Order orderId;

    public Payment(){}

    public Payment(Long id, ZonedDateTime moment, double amountPaid, PaymentMethod paymentMethod, Order orderId) {
        this.id = id;
        this.moment = moment;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.orderId = orderId;
    }

    public Long getPaymentId() {
        return id;
    }

    public void setPaymentId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getMoment() {
        return moment;
    }

    public void setMoment(ZonedDateTime moment) {
        this.moment = moment;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }
}

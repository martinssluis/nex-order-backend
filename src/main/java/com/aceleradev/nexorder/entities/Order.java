package com.aceleradev.nexorder.entities;

import com.aceleradev.nexorder.commons.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ZonedDateTime moment;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @OneToOne(mappedBy = "orderId", cascade = CascadeType.ALL)
    private Payment payment;


    public Order(Integer id, ZonedDateTime moment, OrderStatus orderStatus, Customer customer, Employee employee){
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.employee = employee;
    }

    public Order() {}

    public Employee getEmployeeId() {
        return employee;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employee = employeeId;
    }

    public Customer getCustomerId() {
        return customer;
    }

    public void setCustomerId(Customer customerId) {
        this.customer = customerId;
    }

    public ZonedDateTime getMoment() {
        return moment;
    }

    public void setMoment(ZonedDateTime moment) {
        this.moment = moment;
    }

    public int getOrderId() {
        return id;
    }

    public void setOrderId(int orderId) {
        this.id = orderId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
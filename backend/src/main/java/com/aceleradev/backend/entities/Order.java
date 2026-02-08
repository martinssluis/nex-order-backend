package com.aceleradev.backend.entities;

import com.aceleradev.backend.commons.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ZonedDateTime moment;
    private Integer orderStatus;
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
        this.setOrderStatus(orderStatus);
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

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
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

}
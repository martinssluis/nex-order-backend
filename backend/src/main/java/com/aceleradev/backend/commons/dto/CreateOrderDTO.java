package com.aceleradev.backend.commons.dto;

import com.aceleradev.backend.entities.OrderItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateOrderDTO {

    private Integer orderStatus;
    private Long customerId;
    private Long employeeId;
    private List<OrderItemDto> items = new ArrayList<>();

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCustomer() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployee() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
}

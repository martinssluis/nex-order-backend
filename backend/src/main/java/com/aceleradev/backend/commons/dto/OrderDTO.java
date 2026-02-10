package com.aceleradev.backend.commons.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private String orderStatus;
    private Long customerId;
    private Long employeeId;
    private List<OrderItemDto> items = new ArrayList<>();

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
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

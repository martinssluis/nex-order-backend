package com.aceleradev.backend.controllers;

import com.aceleradev.backend.commons.dto.CreateOrderDTO;
import com.aceleradev.backend.entities.Order;
import com.aceleradev.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDTO createOrderDTO){
        Order order = service.createOrder(createOrderDTO);
        return ResponseEntity.status(201).body(order);
    }

}

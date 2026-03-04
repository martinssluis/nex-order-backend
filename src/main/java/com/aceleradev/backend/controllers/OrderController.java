package com.aceleradev.backend.controllers;

import com.aceleradev.backend.commons.dto.OrderDTO;
import com.aceleradev.backend.entities.Order;
import com.aceleradev.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO){
        Order order = service.createOrder(orderDTO);
        return ResponseEntity.status(201).body(order);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id){
        return service.getOrderById(id);
    }
}

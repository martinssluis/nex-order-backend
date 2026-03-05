package com.aceleradev.nexorder.controllers;

import com.aceleradev.nexorder.dtos.order.OrderDTO;
import com.aceleradev.nexorder.entities.Order;
import com.aceleradev.nexorder.services.OrderService;
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

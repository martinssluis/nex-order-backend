package com.aceleradev.backend.controllers;

import com.aceleradev.backend.commons.dto.PaymentDto;
import com.aceleradev.backend.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService service;

    @PostMapping
    public ResponseEntity makePayment(
            @RequestHeader Long customerId,
            @RequestBody PaymentDto dto
    ) {
        service.makePayment(customerId, dto);
        return ResponseEntity.ok().build();
    }
}

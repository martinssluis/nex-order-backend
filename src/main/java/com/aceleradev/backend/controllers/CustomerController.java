package com.aceleradev.backend.controllers;

import com.aceleradev.backend.commons.dto.CreateCustomerDto;
import com.aceleradev.backend.commons.dto.ReadCustomerDto;
import com.aceleradev.backend.commons.dto.UpdateCustomerDto;
import com.aceleradev.backend.entities.Customer;
import com.aceleradev.backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerDto customerDto) {
        Customer customer = service.createCustomer(customerDto);
        return ResponseEntity.status(201).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<ReadCustomerDto>> findAll(){
        List<ReadCustomerDto> customers = service.findAll();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadCustomerDto> findById(@PathVariable Long id){
        ReadCustomerDto customer = service.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerDto updateCustomerDto) {
        Customer updatedCustomer = service.updateCustomer(id, updateCustomerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        service.deleteCustomer(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

}

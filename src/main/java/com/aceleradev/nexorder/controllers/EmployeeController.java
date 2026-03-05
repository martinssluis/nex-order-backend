package com.aceleradev.nexorder.controllers;

import com.aceleradev.nexorder.dtos.employee.EmployeeDto;
import com.aceleradev.nexorder.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        List<EmployeeDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        EmployeeDto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody EmployeeDto dto) {
        service.createEmployee(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(
            @PathVariable Long id
    ) {
        service.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto dto
    ) {
        service.updateEmployee(id, dto);
    }
}

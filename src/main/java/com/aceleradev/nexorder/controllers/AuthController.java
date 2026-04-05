package com.aceleradev.nexorder.controllers;

import com.aceleradev.nexorder.dtos.LoginRequest;
import com.aceleradev.nexorder.entities.Employee;
import com.aceleradev.nexorder.services.AuthService;
import com.aceleradev.nexorder.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest data) {

      Employee user = authService.authenticate(data.email(), data.password());
      String token = tokenService.generateToken(user);

      return ResponseEntity.ok(token);
      //@PostMapping("/login")
      //public ResponseEntity<String> login(@RequestBody LoginRequest data) {
        //  return ResponseEntity.ok("bateu aqui");
      }
    }

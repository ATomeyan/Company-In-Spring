package com.company.controller;

import com.company.dto.AuthenticationRequest;
import com.company.dto.AuthenticationResponse;
import com.company.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final IAuthenticationService service;

    @Autowired
    public AuthenticationController(IAuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = service.login(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity signOut() {

        return new ResponseEntity(HttpStatus.OK);
    }
}
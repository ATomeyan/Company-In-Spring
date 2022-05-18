package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String userName;
    private String token;
    private EmployeeDto dto;
}
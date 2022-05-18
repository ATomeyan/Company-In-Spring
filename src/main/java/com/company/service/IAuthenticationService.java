package com.company.service;

import com.company.dto.AuthenticationRequest;
import com.company.dto.AuthenticationResponse;

public interface IAuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);
}
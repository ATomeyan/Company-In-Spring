package com.company.service;

import com.company.dto.AuthenticationRequest;
import com.company.dto.AuthenticationResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void logOut(HttpServletRequest request, HttpServletResponse response);
}
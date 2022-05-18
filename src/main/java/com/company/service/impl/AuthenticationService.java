package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.AuthenticationRequest;
import com.company.dto.AuthenticationResponse;
import com.company.entity.User;
import com.company.mapper.EmployeeMapper;
import com.company.repository.UserRepository;
import com.company.service.IAuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthenticationService.class);
    private final EmployeeMapper employeeMapper;
    private final UserRepository repository;

    @Autowired
    public AuthenticationService(UserRepository repository) {
        this.repository = repository;
        employeeMapper = new EmployeeMapper();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {

        String userName = request.getUserName();
        User user = repository.findByUserName(userName);
        String token = "";

        if (user == null || user.getUserName() == null || user.getPassword() == null) {
            LOGGER.error("User by user name {} was not found or invalid:", userName);
            throw new UsernameNotFoundException("User by user name was not found or invalid.");
        }

        return new AuthenticationResponse(userName, token, employeeMapper.entityToDto(user.getEmployee()));
    }
}
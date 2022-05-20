package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.config.JwtToken;
import com.company.dto.AuthenticationRequest;
import com.company.dto.AuthenticationResponse;
import com.company.entity.User;
import com.company.mapper.EmployeeMapper;
import com.company.repository.UserRepository;
import com.company.service.IAuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthenticationService.class);
    private final AuthenticationManager authenticationManager;
    private final EmployeeMapper employeeMapper;
    private final UserRepository repository;
    private final JwtToken jwtToken;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository repository, JwtToken jwtToken) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtToken = jwtToken;
        employeeMapper = new EmployeeMapper();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        String userName = request.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = repository.findByUserName(userName).orElse(null);

        if (user == null) {
            LOGGER.error("User by user name {} was not found or invalid:", userName);
            throw new UsernameNotFoundException("User by user name was not found or invalid.");
        }

        String token = jwtToken.generateToken(userName);

        return new AuthenticationResponse(userName, token, employeeMapper.entityToDto(user.getEmployee()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User by user name was not found or invalid.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), emptyList());
    }
}
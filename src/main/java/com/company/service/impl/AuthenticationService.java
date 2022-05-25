package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.AuthenticationRequest;
import com.company.dto.AuthenticationResponse;
import com.company.entity.Role;
import com.company.entity.User;
import com.company.exceptions.UserAuthenticationException;
import com.company.mapper.EmployeeMapper;
import com.company.repository.UserRepository;
import com.company.security.JwtToken;
import com.company.service.IAuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        String password = request.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (UserAuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        User user = repository.findByUserName(userName).orElse(null);

        if (user == null) {
            LOGGER.error("User by user name {} was not found or invalid:", userName);
            throw new UsernameNotFoundException("User by user name was not found or invalid.");
        }

        String token = jwtToken.createToken(userName);

        return new AuthenticationResponse(userName, token, employeeMapper.entityToDto(user.getEmployee()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User by user name was not found or invalid.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(Objects.requireNonNull(Role.fromName("USER")).toString())));
    }
}
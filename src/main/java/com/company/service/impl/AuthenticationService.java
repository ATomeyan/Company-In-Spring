package com.company.service.impl;

import ch.qos.logback.classic.Logger;
import com.company.dto.AuthenticationRequest;
import com.company.dto.AuthenticationResponse;
import com.company.entity.User;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.UserAuthenticationException;
import com.company.mapper.EmployeeMapper;
import com.company.repository.UserRepository;
import com.company.security.JwtToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService implements com.company.service.AuthenticationService, UserDetailsService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthenticationService.class);
    private final AuthenticationManager authenticationManager;
    private final EmployeeMapper employeeMapper;
    private final UserRepository repository;
    private final JwtToken jwtToken;

    @Autowired
    public AuthenticationService(@Lazy AuthenticationManager authenticationManager, UserRepository repository, JwtToken jwtToken) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtToken = jwtToken;
        employeeMapper = new EmployeeMapper();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (UserAuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        User user = repository.findByUserName(username).orElse(null);

        if (user == null) {
            LOGGER.error("User by user name {} was not found or invalid:", username);
            throw new UsernameNotFoundException("User by user name was not found or invalid.");
        }


        String token = jwtToken.createToken(username);

        return new AuthenticationResponse(username, token);
    }

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null){
            LOGGER.error("User is not logged in");
            throw new NotFoundException("User is not logged in");
        }

        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<SimpleGrantedAuthority> role = new HashSet<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        User user = repository.findByUserName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User by user name was not found or invalid.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), role);
    }
}
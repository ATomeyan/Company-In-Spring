package com.company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtToken jwtToken;

    @Autowired
    public JwtConfigurer(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(jwtToken);
        builder.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtToken jwtToken;

    @Autowired
    public SecurityConfig(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate/**").permitAll()
                .antMatchers("/employees").permitAll()
                .antMatchers(HttpMethod.POST, "/employees/**").hasAnyRole()
                .antMatchers(HttpMethod.PUT, "/employees/**").hasAnyRole()
                .antMatchers(HttpMethod.DELETE, "/employees/**").hasAnyRole()
                .antMatchers("/records/**").permitAll()
                .antMatchers("/positions/**").hasAnyRole()
                .antMatchers("/departments/**").hasAnyRole()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
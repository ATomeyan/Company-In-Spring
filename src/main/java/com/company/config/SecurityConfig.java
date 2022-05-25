package com.company.config;

import com.company.security.JwtConfigurer;
import com.company.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtToken jwtToken;

    @Autowired
    public SecurityConfig(JwtToken jwtToken){
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
                    .csrf().disable()
                    .httpBasic().disable()
                        .authorizeRequests()
                            .antMatchers(HttpMethod.POST, "/authenticate/login").permitAll()
                            .antMatchers("/employees/**").hasRole("USER")
//                            .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("USER")
//                            .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("USER")
                            .antMatchers(HttpMethod.GET, "/employees/**").permitAll()
                            .antMatchers("/positions/**").hasRole("USER")
                            .antMatchers("/departments/**").hasRole("USER")
                            .antMatchers("/records/**").hasRole("USER")
                        .anyRequest().authenticated()
                .and()
                    .apply(new JwtConfigurer(jwtToken));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
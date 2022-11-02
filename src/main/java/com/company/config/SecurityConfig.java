package com.company.config;

import com.company.security.JwtConfigurer;
import com.company.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final JwtToken jwtToken;

    @Autowired
    public SecurityConfig(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                    .csrf().disable()
                    .httpBasic().disable()
                        .authorizeRequests()
                            .antMatchers(HttpMethod.POST, "/authenticate/login").permitAll()
                            .antMatchers(HttpMethod.GET, "/employees/**").permitAll()
                            .antMatchers(HttpMethod.GET, "/download/**").hasRole("USER")
                            .antMatchers(HttpMethod.POST, "/employees/**").hasRole("USER")
                            .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("USER")
                            .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("USER")
                            .antMatchers("/positions/**").hasRole("USER")
                            .antMatchers("/departments/**").permitAll()
                            .antMatchers("/records/**").permitAll()
                        .anyRequest().authenticated()
                    .and()
                        .logout()
                            .logoutUrl("/logout")
                                .deleteCookies("JSESSIONID")
                    .and()
                        .apply(new JwtConfigurer(jwtToken));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
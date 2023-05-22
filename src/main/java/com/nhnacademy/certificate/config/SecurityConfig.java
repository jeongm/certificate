package com.nhnacademy.certificate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .and()
                .logout()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/error/403")
                    .and()
                .build();
    }
}

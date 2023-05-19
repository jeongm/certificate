package com.nhnacademy.certificate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
}

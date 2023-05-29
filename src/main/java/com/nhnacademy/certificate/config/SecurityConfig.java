package com.nhnacademy.certificate.config;

import com.nhnacademy.certificate.auth.CustomLoginSuccessHandler;
import com.nhnacademy.certificate.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                    .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                    .requestMatchers("/certificate").hasAuthority("ROLE_MEMBER")
                    .requestMatchers("/certificate/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER")
                    .requestMatchers("/redirect-index").authenticated()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .usernameParameter("id")
                    .passwordParameter("password")
                    .loginPage("/certificate-login")
                    .loginProcessingUrl("/login")
                    .successHandler(new CustomLoginSuccessHandler())
                    .and()
                .oauth2Login()
                    .authorizedClientService(oAuth2AuthorizedClientService())
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .successHandler(new CustomLoginSuccessHandler())
                    .and()
                .logout()
                    .deleteCookies("SESSION")
                    .invalidateHttpSession(true)
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .accessDeniedPage("/error/403")
                    .and()
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(github());
    }

    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }


    public ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .userNameAttributeName("name")
                .clientId("7dedde138c5b33876fb1")
                .clientSecret("63e2ffcc209bf731021584e52155abef8950740d")
                .build();
    }



}

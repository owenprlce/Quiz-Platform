package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection for simplicity; not recommended for production
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/quizzes/login", "/quizzes/register").permitAll() // Allow unauthenticated access to these endpoints
                .anyRequest().authenticated() // Require authentication for all other endpoints
            )
            .httpBasic(withDefaults()); // Enable HTTP Basic authentication with default configuration
        return http.build();
    }
}
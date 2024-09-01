package com.damitha.gateway.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Slf4j
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring SecurityFilterChain");
        System.out.println("filter chain");
        http
                .csrf().disable()
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Set custom CORS configuration
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/tasks/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/user/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(keycloakJwtAuthenticationConverter)));
        log.info("Configuring SecurityFilterChain complete");
        return http.build();
    }

    // CORS Configuration Bean
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        log.info("cors configuration started");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // Allowed origins (modify as needed)
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Allowed HTTP methods
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Allowed headers
        configuration.setAllowCredentials(true); // Allow credentials (if needed)

        // Register the configuration for all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        log.info("cors configuration completed");
        return source;
    }

}
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // Configure session management to be stateless (no server-side session storage).
//        http.sessionManagement(
//                        management -> management.sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS
//                        )
//                )
//                .authorizeHttpRequests(
//                        authorize -> authorize
//                                .requestMatchers("/api/**").authenticated()  // Protect all endpoints under /api/**
//                                .anyRequest().permitAll()  // Allow other requests without authentication
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt
//                        .jwtAuthenticationConverter(keycloakJwtAuthenticationConverter)))  // Use KeycloakJwtAuthenticationConverter
//                .csrf(csrf -> csrf.disable())  // Disable CSRF as it is not needed for stateless sessions
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()));  // Enable CORS configuration
//
//        // Return the built HttpSecurity configuration.
//        return http.build();
//    }

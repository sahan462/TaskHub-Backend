//package com.damitha.task.manager.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//public class ApplicationConfiguration {
//
//    // This method sets up the security filter chain, defining how HTTP requests should be secured.
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // Configure session management to be stateless (no server-side session storage).
//        http.sessionManagement(
//                        management -> management.sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS
//                        )
//                )
//                // Configure authorization rules: "/api/**" endpoints require authentication, all other requests are permitted.
//                .authorizeHttpRequests(
//                        authorize -> authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll()
//                )
//                // Add a custom filter (JwtTokenValidator) before the BasicAuthenticationFilter to handle JWT validation.
//                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//                // Disable CSRF protection since the application is stateless.
//                .csrf(csrf -> csrf.disable())
//                // Configure CORS (Cross-Origin Resource Sharing) settings.
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                // Enable basic HTTP authentication (username and password prompt).
//                .httpBasic(Customizer.withDefaults());
//
//        // Return the built HttpSecurity configuration.
//        return http.build();
//    }
//
//    // This method sets up the CORS configuration to allow cross-origin requests.
//    private CorsConfigurationSource corsConfigurationSource() {
//        return new CorsConfigurationSource() {
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                CorsConfiguration cfg = new CorsConfiguration();
//
//                // Allow requests from any origin.
//                cfg.setAllowedOrigins(Collections.singletonList("*"));
//                // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.).
//                cfg.setAllowedMethods(Collections.singletonList("*"));
//                // Allow credentials (cookies, authorization headers, etc.) to be included in requests.
//                cfg.setAllowCredentials(true);
//                // Expose all headers to the client.
//                cfg.setAllowedHeaders(Collections.singletonList("*"));
//                // Specifically expose the "Authorization" header to the client.
//                cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                // Set the maximum age (in seconds) for the CORS preflight request cache.
//                cfg.setMaxAge(3600L);
//
//                return cfg;
//            }
//        };
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//}

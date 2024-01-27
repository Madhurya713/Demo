package com.example.demo.config;

import com.example.demo.utils.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final MyUserDetailsService myUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration(MyUserDetailsService myUserDetailsService,
                                 JwtRequestFilter jwtRequestFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF as we are using tokens
                .csrf(csrf -> csrf.disable())

                // Configure session management to stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configure URL authorization
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/jwt/**").permitAll()
                        .requestMatchers("/authenticate").permitAll()  // Permit all for authentication endpoint
                        .requestMatchers("/admin/**").permitAll()/*.hasRole("ADMIN")*/ // Only users with ROLE_ADMIN can access /admin/**
                        .requestMatchers("/user/**").permitAll()/*.hasRole("USER")  */ // Only users with ROLE_USER can access /user/**
                        .anyRequest().authenticated()                  // All other requests need to be authenticated
                )

                // Add JWT token filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    @Primary
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }
}

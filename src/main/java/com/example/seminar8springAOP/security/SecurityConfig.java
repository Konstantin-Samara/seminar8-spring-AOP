package com.example.seminar8springAOP.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
//                                .requestMatchers(HttpMethod.DELETE).hasRole("role_ADMIN")
//                                .requestMatchers(HttpMethod.POST).hasRole("role_ADMIN")
//                                .requestMatchers(HttpMethod.PUT).hasRole("role_ADMIN")
//                                .requestMatchers(HttpMethod.GET).hasAnyRole("role_ADMIN","role_USER")
                                .requestMatchers("/api/commands/admin/**").hasRole("role_ADMIN")
                                .requestMatchers("/api/commands/user/**").hasAnyRole("role_ADMIN","role_USER")
                                .requestMatchers("/api/performers/admin/**").hasRole("role_ADMIN")
                                .requestMatchers("/api/performers/user/**").hasAnyRole("role_ADMIN","role_USER")
//                                .requestMatchers("/login/**").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();




    }
}

package com.jamersc.springboot.hcm_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

    // Inject your custom UserDetailsService
    final private UserDetailsService userDetailsService;

    // Constructor injection for UserDetailsService
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    // 1. Password Encoder Bean (Crucial for Hashing Passwords)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. DaoAuthenticationProvider Bean
    // This provider will use your UserDetailsService and PasswordEncoder to authenticate users.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Set your custom UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Set the password
        return authProvider;
    }

    // Expose AuthenticationManager as a Bean
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        // public access
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        // Spring Security's default login form endpoint (if you use it), or just allow basic auth
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() // Default login endpoint if you configure formLogin()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll() // Your registration endpoint
                        // Note: Always check the end point for each HTTP method
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees/").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                        // Add specific rules for applicant-related endpoints if they are different from employees
                        .requestMatchers(HttpMethod.POST, "/api/applicants/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/api/applicants/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.GET, "/api/applicants/**").hasRole("APPLICANT") // Example: Applicant viewing own profile
                        // All other requests require authentication
                        .anyRequest().authenticated()
        );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Configure HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable CSRF for stateless REST APIs (as you already have)
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}

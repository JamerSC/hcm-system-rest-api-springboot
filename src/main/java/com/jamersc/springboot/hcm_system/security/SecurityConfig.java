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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    // Constructor injection for UserDetailsService
    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
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

        http
                // 1. Disable CSRF (Stateless APIs don't need it)
                .csrf(AbstractHttpConfigurer::disable)
                // 2. Configure endpoint access rules
                .authorizeHttpRequests(configurer ->
                configurer
                        // public access
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                        // Spring Security's default login form endpoint (if you use it), or just allow basic auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll() // Default login endpoint if you configure formLogin()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll() // Your registration endpoint
                        // Note: Always check the end point for each HTTP method
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/employees/").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/employees/").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/employees/**").hasRole("ADMIN")
                        // Add specific rules for applicant-related endpoints if they are different from employees
                        .requestMatchers(HttpMethod.GET, "/api/v1/recruitments/**").hasRole("MANAGER") // get applicant profile
                        .requestMatchers(HttpMethod.GET, "/api/v1/applicants/**").hasRole("APPLICANT") // get applicant profile
                        .requestMatchers(HttpMethod.POST, "/api/v1/applicants/**").hasRole("APPLICANT") // applicants resume
                        .requestMatchers(HttpMethod.PUT, "/api/v1/applicants/**").hasRole("APPLICANT") // applicant profile update
                        // All other requests require authentication
                        .anyRequest().authenticated()
        );
        // 3. Configure session management to be STATELESS
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 4. Remove default HTTP Basic auth (no longer needed)
        http.httpBasic(Customizer.withDefaults())
        // 5. Add our custom JWT filter BEFORE the default Spring Security filters
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Disable CSRF for stateless REST APIs (as you already have)
        // http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}

package com.jamersc.springboot.hcm_api.service.user;

import com.jamersc.springboot.hcm_api.entity.Role;
import com.jamersc.springboot.hcm_api.entity.User;
import com.jamersc.springboot.hcm_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Username not found: " + username));
        // convert user entity into spring security's UserDetails object
        return new org.springframework.security.core.userdetails.User(
          user.getUsername(),
          user.getPassword(),  // This is the HASHED password from the DB
          mapRolesToAuthorities(user.getRoles()) // Map your custom Roles to Spring Security Authorities
        );
    }

    // Helper method to convert your Role entities into GrantedAuthority objects
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())) // Role name should include "ROLE_" prefix
                .collect(Collectors.toList());
    }
}

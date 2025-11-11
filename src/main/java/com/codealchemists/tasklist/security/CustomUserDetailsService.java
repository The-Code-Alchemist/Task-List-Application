package com.codealchemists.tasklist.security;

import com.codealchemists.tasklist.model.User;
import com.codealchemists.tasklist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));

        // Spring Security's User implementation
        return org.springframework.security.core.userdetails.User
                .withUsername(user.username())
                .password(user.passwordHash())
                .roles("USER")
                .build();
    }
}

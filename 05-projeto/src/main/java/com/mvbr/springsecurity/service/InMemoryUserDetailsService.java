package com.mvbr.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {

    public static final String USER_NOT_FOUND_MESSAGE = "User not found";

    private final List<UserDetails> users;

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return users.stream()
            .filter(
                u -> u.getUsername().equals(username)
            )
            .findFirst()
            .orElseThrow(
                () -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE)
            );
    }

}

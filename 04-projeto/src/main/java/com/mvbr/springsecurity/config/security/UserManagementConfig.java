package com.mvbr.springsecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {

    public static final String USER_AUTHENTICATED = "joao";
    public static final String PASSWORD_AUTHENTICATED = "123456";
    private static final String ROLE_READ = "read";

    @Bean
    public UserDetailsService userDetailsService() {

        var userDetailsService = new InMemoryUserDetailsManager();

        var user = User.withUsername(USER_AUTHENTICATED)
            .password(PASSWORD_AUTHENTICATED)
            .authorities(ROLE_READ)
            .build();

        userDetailsService.createUser(user);

        return userDetailsService;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}

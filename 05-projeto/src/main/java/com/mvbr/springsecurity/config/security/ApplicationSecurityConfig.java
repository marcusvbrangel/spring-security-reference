package com.mvbr.springsecurity.config.security;

import com.mvbr.springsecurity.model.UserCustomized;
import com.mvbr.springsecurity.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class ApplicationSecurityConfig  {

    public static final String USER_AUTHENTICATED = "joao";
    public static final String PASSWORD_AUTHENTICATED = "123456";
    public static final String READ_ROLE = "read";

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = new UserCustomized(USER_AUTHENTICATED, PASSWORD_AUTHENTICATED, READ_ROLE);

        List<UserDetails> users = List.of(user);

        return new InMemoryUserDetailsService(users);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}

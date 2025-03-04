package com.mvbr.springsecurity.config.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public static final String USER_AUTHENTICATED = "joao";
    public static final String PASSWORD_AUTHENTICATED = "123456";
    public static final String MESSAGE_AUTHENTICATION_NOT_FOUND = "Invalid username or password";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if (USER_AUTHENTICATED.equals(username) && PASSWORD_AUTHENTICATED.equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        } else {
            throw new AuthenticationCredentialsNotFoundException(MESSAGE_AUTHENTICATION_NOT_FOUND);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken
            .class
            .isAssignableFrom(authentication);
    }

}

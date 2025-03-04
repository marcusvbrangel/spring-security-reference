package com.mvbr.springsecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public WebAuthorizationConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.authenticationProvider(customAuthenticationProvider);

        httpSecurity.authorizeHttpRequests(
            authorize -> authorize.anyRequest().authenticated()
        );

        return httpSecurity.build();

    }

}

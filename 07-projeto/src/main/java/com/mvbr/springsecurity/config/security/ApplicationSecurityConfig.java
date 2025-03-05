package com.mvbr.springsecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationSecurityConfig {

    public static final String NOOP_ENCODER = "noop";
    public static final String BCRYPT_ENCODER = "bcrypt";
    public static final String SCRYPT_ENCODER = "scrypt";

    private static final int BCRYPT_ENCODER_DEFAULT_CPU_COST = 65536;
    private static final int BCRYPT_ENCODER_DEFAULT_MEMORY_COST = 8;
    private static final int BCRYPT_ENCODER_DEFAULT_PARALLELISM = 1;
    private static final int BCRYPT_ENCODER_DEFAULT_KEY_LENGTH = 32;
    private static final int BCRYPT_ENCODER_DEFAULT_SALT_LENGTH = 16;

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        final String usersByUsernameQuery = "select username, password, enabled from spring.users where username = ?";
        final String authsByUserQuery = "select username, authority from spring.authorities where username = ?";

        var userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);

        return userDetailsManager;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put(NOOP_ENCODER, NoOpPasswordEncoder.getInstance());
        encoders.put(BCRYPT_ENCODER, new BCryptPasswordEncoder());
        encoders.put(SCRYPT_ENCODER, new SCryptPasswordEncoder(
            BCRYPT_ENCODER_DEFAULT_CPU_COST,
            BCRYPT_ENCODER_DEFAULT_MEMORY_COST,
            BCRYPT_ENCODER_DEFAULT_PARALLELISM,
            BCRYPT_ENCODER_DEFAULT_KEY_LENGTH,
            BCRYPT_ENCODER_DEFAULT_SALT_LENGTH
        ));

        return new DelegatingPasswordEncoder(NOOP_ENCODER, encoders);

    }

}

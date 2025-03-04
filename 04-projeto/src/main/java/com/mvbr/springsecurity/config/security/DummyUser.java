package com.mvbr.springsecurity.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DummyUser implements UserDetails {

    public static final String USER_AUTHENTICATED = "joao";
    public static final String PASSWORD_AUTHENTICATED = "123456";
    public static final String READ_ROLE = "read";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> READ_ROLE);
    }

    @Override
    public String getPassword() {
        return PASSWORD_AUTHENTICATED;
    }

    @Override
    public String getUsername() {
        return USER_AUTHENTICATED;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

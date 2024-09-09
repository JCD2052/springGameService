package org.jcd2052.api.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getUserRole() == UserRole.ADMIN
                ? Arrays.stream(UserRole.values()).map(role -> (GrantedAuthority) role::name).toList()
                : List.of((GrantedAuthority) () -> user.getUserRole().name());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}

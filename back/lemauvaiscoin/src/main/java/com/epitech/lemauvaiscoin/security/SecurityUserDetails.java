package com.epitech.lemauvaiscoin.security;

import com.epitech.lemauvaiscoin.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class SecurityUserDetails implements UserDetails {

    private final Long id;

    private final String username;

    private final String password;

    private final String mail;

    private final Collection<? extends GrantedAuthority> authorities;

    public static SecurityUserDetails build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new SecurityUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getMail(),
                authorities);
    }

    public List<GrantedAuthority> getRoles() {
        return new ArrayList<>(this.getAuthorities());
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

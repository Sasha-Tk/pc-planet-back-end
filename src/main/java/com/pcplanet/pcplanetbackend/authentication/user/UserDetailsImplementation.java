package com.pcplanet.pcplanetbackend.authentication.user;

import com.pcplanet.pcplanetbackend.user_information.UserInformation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@RequiredArgsConstructor
public class UserDetailsImplementation implements UserDetails {
    private final UserInformation userInformation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userInformation.getUserRole().name()));
    }

    @Override
    public String getPassword() {
        return this.userInformation.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userInformation.getEmail();
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

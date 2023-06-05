package com.rails.railway.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@Builder
public class LoginUserDetails implements UserDetails {

    private Long id;
    private String username;

    private String password;
    private String email;
    private LocalDate lastLoginDate;
    private String phoneNumber;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(Long id, String username, String password, String email,
                            LocalDate lastLoginDate, String phoneNumber,
                            Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastLoginDate = lastLoginDate;
        this.authorities = roles;
    }

    public static UserDetails build(User user, Collection<? extends GrantedAuthority> roles) {
            return new LoginUserDetails(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getLastLoginDate(),
                    user.getPhoneNumber(),
                    roles);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

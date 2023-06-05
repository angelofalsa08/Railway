package com.rails.railway.service;

import com.rails.railway.model.LoginUserDetails;
import com.rails.railway.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.rails.railway.model.User user = userRepository.findByUsername(username);

        if (user != null) {
            Collection<? extends GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            return LoginUserDetails.build(user,roles);
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}

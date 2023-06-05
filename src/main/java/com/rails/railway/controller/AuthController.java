package com.rails.railway.controller;

import com.rails.railway.dto.requests.LoginRequest;
import com.rails.railway.exceptions.AuthenticationException;
import com.rails.railway.model.LoginUserDetails;
import com.rails.railway.model.User;
import com.rails.railway.service.UserService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    // handler method to handle register user form submit request
    @PostMapping("/register")
    public ResponseEntity<?> registration(@Valid @RequestBody User user){
        User existing = userService.findByUsername(user.getUsername());
        if (existing != null) {
           return ResponseEntity.badRequest().body("Existing user");
        }
        userService.saveUser(user);
        return ResponseEntity.ok().body("Successfully registered");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication;
        try {
            if(loginRequest.isGuest()){
                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, "GUEST")
                        .header(HttpHeaders.SET_COOKIE, UUID.randomUUID().toString())
                        .build();
            }else{
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

                if (authentication.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    LoginUserDetails authUser =  (LoginUserDetails) authentication.getPrincipal();

                    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, authUser.getUsername())
                            .header(HttpHeaders.SET_COOKIE,String.valueOf(authUser.getId()))
                            .build();
                }
            }

        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
        return null;
    }

}

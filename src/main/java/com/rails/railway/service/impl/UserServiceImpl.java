package com.rails.railway.service.impl;

import com.rails.railway.model.User;
import com.rails.railway.repository.UserRepository;
import com.rails.railway.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        User register = new User();
        register.setUsername(user.getUsername());
        register.setEmail(user.getEmail());
        register.setPhoneNumber(user.getPhoneNumber());
        register.setRegistrationDate(LocalDate.now());
        register.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(register);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}

package com.rails.railway.service;

import com.rails.railway.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    User findByUsername(String username);
    List<User> findAllUser();
}

package com.doog.service;

import com.doog.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUsername(String username);

    void register(String username, String password);
}

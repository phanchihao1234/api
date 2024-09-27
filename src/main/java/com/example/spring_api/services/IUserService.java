package com.example.spring_api.services;

import com.example.spring_api.models.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserByUsername(String username);
    String login(String username, String password);
}

package com.sample.user_service.service;

import java.util.List;
import java.util.UUID;

import com.sample.user_service.entity.User;

public interface UserService {
    User save(User user);

    List<User> searchByUsername(String username);

    User updateUser(UUID id, User newUserDetails);

    void deleteUser(UUID id);

    Iterable<User> getAllUsers();

    User getUserById(UUID id);
    
}
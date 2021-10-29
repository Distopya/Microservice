package com.dystopia.userservice.core.service;

import com.dystopia.userservice.config.model.Follow;
import com.dystopia.userservice.config.model.Post;
import com.dystopia.userservice.core.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(String userId);

    Optional<User> getUserByUsername(String username);

    User saveUser(User user);

    void deleteUser(String userId);

    Follow saveFollow(String userId, Follow follow);

    Post savePost(String userId, Post post);
}

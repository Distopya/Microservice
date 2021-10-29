package com.dystopia.userservice.service;

import com.dystopia.userservice.config.client.FollowFeignClient;
import com.dystopia.userservice.config.client.PostFeignClient;
import com.dystopia.userservice.config.model.Follow;
import com.dystopia.userservice.config.model.Post;
import com.dystopia.userservice.core.entity.User;
import com.dystopia.userservice.core.repository.UserRepository;
import com.dystopia.userservice.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowFeignClient followFeignClient;

    @Autowired
    private PostFeignClient postFeignClient;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public Follow saveFollow(String userId, Follow follow) {
        follow.setReader(userId);
        return followFeignClient.insertFollow(follow);
    }

    @Override
    @Transactional
    public Post savePost(String userId, Post post) {
        post.setUser(userId);
        return postFeignClient.insertPost(post);
    }
}

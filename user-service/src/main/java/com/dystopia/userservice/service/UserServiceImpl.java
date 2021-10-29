package com.dystopia.userservice.service;

import com.dystopia.userservice.config.client.FollowFeignClient;
import com.dystopia.userservice.config.model.Follow;
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
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Follow saveFollow(String userId, Follow follow) {
        follow.setReader(userId);
        //        follow.setWriter(writer);
        Follow followNew = followFeignClient.insertFollow(follow);
        return followNew;
    }
}

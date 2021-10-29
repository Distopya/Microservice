package com.dystopia.followservice.service;

import com.dystopia.followservice.core.entity.Follow;
import com.dystopia.followservice.core.repository.FollowRepository;
import com.dystopia.followservice.core.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    @Override
    public Optional<Follow> getFollowById(String followId) {
        return followRepository.findById(followId);
    }

    @Override
    public List<Follow> getFollowByReader(String reader) {
        return followRepository.findByReader(reader);
    }

    @Override
    public List<Follow> getFollowByWriter(String writer) {
        return followRepository.findByWriter(writer);
    }

    @Override
    public Follow saveFollow(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public void deleteFollow(String followId) {
        followRepository.deleteById(followId);
    }
}

package com.dystopia.followservice.core.service;

import com.dystopia.followservice.core.entity.Follow;

import java.util.List;
import java.util.Optional;

public interface FollowService {
    List<Follow> getAllFollows();

    Optional<Follow> getFollowById(String followId);

    List<Follow> getFollowByReader(String reader);

    List<Follow> getFollowByWriter(String writer);

    Follow saveFollow(Follow follow);

    void deleteFollow(String followId);

    //    Follow createFollow(String reader, Follow follow);
}

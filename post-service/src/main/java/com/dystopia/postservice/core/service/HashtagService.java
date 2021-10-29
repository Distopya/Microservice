package com.dystopia.postservice.core.service;

import com.dystopia.postservice.core.entity.Hashtag;

import java.util.List;
import java.util.Optional;

public interface HashtagService {
    List<Hashtag> getAllHashtags();

    Optional<Hashtag> getHashtagById(String hashtagId);

    Optional<Hashtag> getHashtagByName(String name);

    Hashtag saveHashtag(Hashtag hashtag);

    void deleteHashtag(String hashtagId);
}

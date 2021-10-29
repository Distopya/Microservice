package com.dystopia.postservice.service;

import com.dystopia.postservice.core.entity.Hashtag;
import com.dystopia.postservice.core.repository.HashtagRepository;
import com.dystopia.postservice.core.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public List<Hashtag> getAllHashtags() {
        return hashtagRepository.findAll();
    }

    @Override
    public Optional<Hashtag> getHashtagById(String hashtagId) {
        return hashtagRepository.findById(hashtagId);
    }

    @Override
    public Optional<Hashtag> getHashtagByHashtagName(String name) {
        return hashtagRepository.findByHashtagName(name);
    }

    @Override
    @Transactional
    public Hashtag saveHashtag(Hashtag hashtag) {
        return hashtagRepository.save(hashtag);
    }

    @Override
    @Transactional
    public void deleteHashtag(String hashtagId) {
        hashtagRepository.deleteById(hashtagId);
    }
}

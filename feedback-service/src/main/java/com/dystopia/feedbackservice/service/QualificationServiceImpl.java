package com.dystopia.feedbackservice.service;

import com.dystopia.feedbackservice.config.client.PostFeignClient;
import com.dystopia.feedbackservice.config.client.UserFeignClient;
import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Qualification;
import com.dystopia.feedbackservice.core.repository.QualificationRepository;
import com.dystopia.feedbackservice.core.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QualificationServiceImpl implements QualificationService {
    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private PostFeignClient postFeignClient;

    @Override
    public List<Qualification> getAllQualificationsByPostId(String postId) {
        return qualificationRepository.findByPost(postId);
    }

    @Override
    public List<Qualification> getAllQualificationsByUserId(String userId) {
        return qualificationRepository.findByUser(userId);
    }

    @Override
    public Optional<Qualification> getQualificationById(String qualificationId) {
        return qualificationRepository.findById(qualificationId);
    }

    @Override
    @Transactional
    public Qualification saveQualification(Qualification qualification, String userId, String postId) {
        qualification.setUser(userId);
        qualification.setPost(postId);
        return qualificationRepository.save(qualification);
    }

    @Override
    @Transactional
    public void deleteQualification(String qualificationId) {
        qualificationRepository.deleteById(qualificationId);
    }

    @Override
    public User findUserById(String userId) {
        return userFeignClient.findUserById(userId);
    }

    @Override
    public Post findPostById(String postId) {
        return postFeignClient.findPostById(postId);
    }
}

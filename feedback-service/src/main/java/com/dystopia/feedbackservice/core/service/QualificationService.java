package com.dystopia.feedbackservice.core.service;

import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Qualification;

import java.util.List;
import java.util.Optional;

public interface QualificationService {
    List<Qualification> getAllQualificationsByPostId(String postId);

    List<Qualification> getAllQualificationsByUserId(String userId);

    Optional<Qualification> getQualificationById(String qualificationId);

    Qualification saveQualification(Qualification qualification, String userId, String postId);

    void deleteQualification(String qualificationId);

    User findUserById(String userId);

    Post findPostById(String postId);
}

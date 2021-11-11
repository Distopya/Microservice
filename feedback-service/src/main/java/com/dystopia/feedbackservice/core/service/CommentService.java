package com.dystopia.feedbackservice.core.service;

import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllCommentsByPostId(String postId);

    List<Comment> getAllCommentsByUserId(String userId);

    Optional<Comment> getCommentById(String commentId);

    Comment saveComment(Comment comment, String userId, String postId);

    void deleteComment(String commentId);

    User findUserById(String userId);

    Post findPostById(String postId);
}

package com.dystopia.feedbackservice.core.service;

import com.dystopia.feedbackservice.core.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllCommentsByPostId(String postId);

    List<Comment> getAllCommentsByUserId(String userId);

    Optional<Comment> getCommentById(String commentId);

    Comment saveComment(String userId, String postId, Comment comment);

    void deleteComment(String commentId);

    Comment getCommentByUserIdAndPostId(String userId, String postId);


}

package com.dystopia.feedbackservice.service;

import com.dystopia.feedbackservice.config.client.PostFeignClient;
import com.dystopia.feedbackservice.config.client.UserFeignClient;
import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Comment;
import com.dystopia.feedbackservice.core.repository.CommentRepository;
import com.dystopia.feedbackservice.core.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private PostFeignClient postFeignClient;

    @Override
    public List<Comment> getAllCommentsByPostId(String postId) {
        return commentRepository.findByPost(postId);
    }

    @Override
    public List<Comment> getAllCommentsByUserId(String userId) {
        return commentRepository.findByUser(userId);
    }

    @Override
    public Optional<Comment> getCommentById(String commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    @Transactional
    public Comment saveComment(Comment comment, String userId, String postId) {
        comment.setUser(userId);
        comment.setPost(postId);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
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

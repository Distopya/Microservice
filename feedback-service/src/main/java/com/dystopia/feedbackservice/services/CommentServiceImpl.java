package com.dystopia.feedbackservice.services;

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
    public Comment saveComment(String userId, String postId, Comment comment) {
        comment.setUser(userId);
        comment.setPost(postId);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String commentId) {
            commentRepository.deleteById(commentId);

    }

    @Override
    public Comment getCommentByUserIdAndPostId(String userId, String postId) {
        return null;
    }
}

package com.dystopia.feedbackservice.controller;

import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Comment;
import com.dystopia.feedbackservice.core.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> findAllCommentsByPostId(@PathVariable String postId) {
        try {
            List<Comment> comments = commentService.getAllCommentsByPostId(postId);
            if (comments.size() > 0)
                return ResponseEntity.ok(comments);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<Comment>> findAllCommentsByUserId(@PathVariable String userId) {
        try {
            List<Comment> comments = commentService.getAllCommentsByUserId(userId);
            if (comments.size() > 0)
                return ResponseEntity.ok(comments);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/users/{userId}/posts/{postId}/comments")
    public ResponseEntity<Comment> insertComment(@PathVariable("userId") String userId, @PathVariable("postId") String postId, @Valid @RequestBody Comment comment) {
        try {
            User userExist = commentService.findUserById(userId);
            Post postExist = commentService.findPostById(postId);
            if (userExist != null && postExist != null) {
                Comment commentNew = commentService.saveComment(comment, userId, postId);
                return ResponseEntity.status(HttpStatus.CREATED).body(commentNew);
            } else return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/users/{userId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateUser(
            @PathVariable("userId") String userId, @PathVariable("postId") String postId, @PathVariable("commentId") String commentId, @Valid @RequestBody Comment comment) {
        try {
            Optional<Comment> commentUp = commentService.getCommentById(commentId);
            if (commentUp.isEmpty())
                return ResponseEntity.notFound().build();
            commentService.saveComment(comment, userId, postId);
            comment.setId(commentId);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("commentId") String commentId) {
        try {
            Optional<Comment> commentDelete = commentService.getCommentById(commentId);
            if (commentDelete.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            commentService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

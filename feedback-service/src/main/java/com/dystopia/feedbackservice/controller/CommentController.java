package com.dystopia.feedbackservice.controller;

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
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/users/{userId}/posts/{postId}/comments")
    public ResponseEntity<Comment> insertComment(
            @PathVariable(name = "userId") String user,
            @PathVariable(name = "postId") String post,
            @Valid @RequestBody Comment comment) {
        try {
            Comment commentNew = commentService.saveComment(user,post,comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentNew);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByPostId(
            @PathVariable(name = "postId") String  postId) {
    try {
    List<Comment> comments = commentService.getAllCommentsByPostId(postId);
    if (comments.size() > 0)
        return ResponseEntity.ok(comments);
    else
        return ResponseEntity.noContent().build();
    }catch (Exception e){
        return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByUserId(
            @PathVariable(name = "userId") String userId) {
        try {
            List<Comment> comments = commentService.getAllCommentsByUserId(userId);
            if (comments.size() > 0)
                return ResponseEntity.ok(comments);
            else
                return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") String commentId){
           try {
        Optional<Comment> comment = commentService.getCommentById(commentId);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
           }
    }


    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable(name = "commentId") String commentId,
            @PathVariable(name = "userId") String userId,
            @PathVariable(name = "postId") String postId,
            @Valid @RequestBody Comment comment) {
        try {
            Optional<Comment> commentUp = commentService.getCommentById(commentId);
            if (commentUp.isEmpty())
                return ResponseEntity.notFound().build();
            comment.setId(commentId);
            comment.setUser(userId);
            comment.setPost(postId);
            commentService.saveComment(userId,postId,comment);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(
            @PathVariable(name = "commentId") String commentId) {
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

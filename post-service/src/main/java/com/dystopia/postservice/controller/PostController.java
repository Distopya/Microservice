package com.dystopia.postservice.controller;

import com.dystopia.postservice.core.entity.Post;
import com.dystopia.postservice.core.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping()
    public ResponseEntity<List<Post>> findAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            if (posts.size() > 0)
                return ResponseEntity.ok(posts);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable("postId") String postId) {
        try {
            Optional<Post> post = postService.getPostById(postId);
            return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Post> insertPost(@RequestBody Post post) {
        try {
            Post postNew = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(postNew);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") String postId, @Valid @RequestBody Post post) {
        try {
            Optional<Post> postUp = postService.getPostById(postId);
            if (postUp.isEmpty())
                return ResponseEntity.notFound().build();
            post.setId(postId);
            postService.savePost(post);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable("postId") String postId) {
        try {
            Optional<Post> postDelete = postService.getPostById(postId);
            if (postDelete.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            postService.deletePost(postId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}


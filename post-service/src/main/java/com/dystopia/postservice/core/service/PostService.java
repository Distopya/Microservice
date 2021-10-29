package com.dystopia.postservice.core.service;

import com.dystopia.postservice.core.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();

    Optional<Post> getPostById(String postId);

    Optional<Post> getPostByTitle(String title);

    Post savePost(Post post);

    void deletePost(String postId);
}

package com.dystopia.postservice.service;

import com.dystopia.postservice.core.entity.Post;
import com.dystopia.postservice.core.repository.PostRepository;
import com.dystopia.postservice.core.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(String postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Optional<Post> getPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    @Override
    @Transactional
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }

}

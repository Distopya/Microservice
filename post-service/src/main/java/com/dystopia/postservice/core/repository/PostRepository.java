package com.dystopia.postservice.core.repository;

import com.dystopia.postservice.core.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findByTitle(String title);
}

package com.dystopia.feedbackservice.core.repository;

import com.dystopia.feedbackservice.core.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPost(String post);

    List<Comment> findByUser(String user);

    Optional<Comment> findByUserAndPost(String user, String post);
}

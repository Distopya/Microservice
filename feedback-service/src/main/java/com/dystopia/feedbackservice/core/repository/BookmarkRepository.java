package com.dystopia.feedbackservice.core.repository;

import com.dystopia.feedbackservice.core.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, String> {
    List<Bookmark> findByPost(String post);

    List<Bookmark> findByUser(String user);

    Optional<Bookmark> findByUserAndPost(String user, String post);
}

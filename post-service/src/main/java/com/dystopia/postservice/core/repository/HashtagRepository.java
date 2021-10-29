package com.dystopia.postservice.core.repository;

import com.dystopia.postservice.core.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, String>{
    Optional<Hashtag> findByName(String name);
}

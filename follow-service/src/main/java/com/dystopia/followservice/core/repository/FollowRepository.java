package com.dystopia.followservice.core.repository;

import com.dystopia.followservice.core.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {
    List<Follow> findByReader(String reader);

    List<Follow> findByWriter(String writer);
}

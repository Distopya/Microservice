package com.dystopia.feedbackservice.core.repository;

import com.dystopia.feedbackservice.core.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, String> {
    List<Qualification> findByPost(String post);

    List<Qualification> findByUser(String user);

    Optional<Qualification> findByUserAndPost(String user, String post);
}

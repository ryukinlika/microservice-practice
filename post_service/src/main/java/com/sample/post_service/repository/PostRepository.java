package com.sample.post_service.repository;

import com.sample.post_service.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {

    @Query("SELECT p FROM Post p WHERE p.userId = :userId")
    List<Post> findByUserId(@Param("userId") UUID userId);

    @Query("SELECT p FROM Post p WHERE p.id = :id")
    Optional<Post> findById(@Param("id") UUID id);
}
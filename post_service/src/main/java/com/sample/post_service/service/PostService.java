package com.sample.post_service.service;

import com.sample.post_service.entity.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(UUID id, Post post);

    void deletePost(UUID id);

    List<Post> findByUserId(UUID userId);

    Optional<Post> findById(UUID id);
}
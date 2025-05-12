package com.sample.post_service.service.impl;

import com.sample.post_service.entity.Post;
import com.sample.post_service.repository.PostRepository;
import com.sample.post_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(UUID id, Post post) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isEmpty()) {
            throw new IllegalArgumentException("Post with ID " + id + " does not exist.");
        }
        Post updatedPost = existingPost.get();
        updatedPost.setTextContent(post.getTextContent());
        updatedPost.setMediaIds(post.getMediaIds());
        return postRepository.save(updatedPost);
    }

    @Override
    public void deletePost(UUID id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("Post with ID " + id + " does not exist.");
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findByUserId(UUID userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id);
    }
}
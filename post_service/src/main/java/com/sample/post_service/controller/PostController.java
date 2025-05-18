package com.sample.post_service.controller;

import com.sample.post_service.entity.Post;
import com.sample.post_service.service.PostService;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/user/{userId}")
    public List<Post> findByUserId(@PathVariable String userId) throws BadRequestException {
        if (!isValidUUID(userId)) {
            throw new BadRequestException("Invalid UUID format for userId: " + userId);
        }
        return postService.findByUserId(UUID.fromString(userId));
    }

    @GetMapping("/{id}")
    public Optional<Post> findById(@PathVariable String id) throws BadRequestException {
        if (!isValidUUID(id)) {
            throw new BadRequestException("Invalid UUID format for id: " + id);
        }
        return postService.findById(UUID.fromString(id));
    }

    @PostMapping
    public Post createPost(@RequestParam String userId, @RequestParam String textContent, @RequestParam(required = false) String mediaIds) throws BadRequestException {
        if (!isValidUUID(userId)) {
            throw new BadRequestException("Invalid UUID format for userId: " + userId);
        }
        UUID userUUID = UUID.fromString(userId);
        Post post = new Post(userUUID, textContent, mediaIds);
        return postService.createPost(post);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable String id) throws BadRequestException {
        if (!isValidUUID(id)) {
            throw new BadRequestException("Invalid UUID format for id: " + id);
        }
        
        try {
            postService.deletePost(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private boolean isValidUUID(String uuid) {
        try {
            return UUID.fromString(uuid).toString().equals(uuid);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
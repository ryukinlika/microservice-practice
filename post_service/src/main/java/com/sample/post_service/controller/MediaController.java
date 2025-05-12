package com.sample.post_service.controller;

import com.sample.post_service.entity.Media;
import com.sample.post_service.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping
    public Media createMedia(@RequestParam String userId, @RequestParam byte[] blobContent) {
        if (!isValidUUID(userId)) {
            throw new IllegalArgumentException("Invalid UUID format for userId: " + userId);
        }
        UUID userUUID = UUID.fromString(userId);
        Media media = new Media(userUUID, blobContent);
        return mediaService.createMedia(media);
    }

    @GetMapping("/{id}")
    public Optional<Media> findById(@PathVariable String id) {
        return mediaService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Media> findByUserId(@PathVariable String userId) {
        if (!isValidUUID(userId)) {
            throw new IllegalArgumentException("Invalid UUID format for userId: " + userId);
        }
        UUID userUUID = UUID.fromString(userId);
        return mediaService.findByUserId(userUUID);
    }

    @DeleteMapping("/{id}")
    public void deleteMedia(@PathVariable String id) {
        mediaService.deleteMedia(id);
    }

    private boolean isValidUUID(String uuid) {
        try {
            return UUID.fromString(uuid).toString().equals(uuid);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
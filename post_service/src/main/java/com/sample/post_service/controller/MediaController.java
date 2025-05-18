package com.sample.post_service.controller;

import com.sample.post_service.entity.Media;
import com.sample.post_service.service.MediaService;

import org.apache.coyote.BadRequestException;
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
    public Media createMedia(@RequestParam String userId, @RequestBody byte[] blobContent) throws BadRequestException {
        if (!isValidUUID(userId)) {
            throw new BadRequestException("Invalid UUID format for userId: " + userId);
        }
        UUID userUUID = UUID.fromString(userId);
        Media media = new Media(userUUID, blobContent);
        return mediaService.createMedia(media);
    }

    @GetMapping("/{id}")
    public Optional<Media> findById(@PathVariable String id) throws BadRequestException {
        if (!isValidUUID(id)) {
            throw new BadRequestException("Invalid UUID format for mediaId: " + id);
        }
        UUID mediaUUID = UUID.fromString(id);
        return mediaService.findById(mediaUUID);
    }

    @GetMapping("/user/{userId}")
    public List<Media> findByUserId(@PathVariable String userId) throws BadRequestException {
        if (!isValidUUID(userId)) {
            throw new BadRequestException("Invalid UUID format for userId: " + userId);
        }
        UUID userUUID = UUID.fromString(userId);
        return mediaService.findByUserId(userUUID);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMedia(@PathVariable String id) throws BadRequestException {
        if (!isValidUUID(id)) {
            throw new BadRequestException("Invalid UUID format for mediaId: " + id);
        }
        UUID mediaUUID = UUID.fromString(id);
        try {
            mediaService.deleteMedia(mediaUUID);
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
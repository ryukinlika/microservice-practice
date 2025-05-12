package com.sample.post_service.service;

import com.sample.post_service.entity.Media;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediaService {

    Media createMedia(Media media);

    Optional<Media> findById(UUID id);

    List<Media> findByUserId(UUID userId);

    void deleteMedia(UUID id);
}
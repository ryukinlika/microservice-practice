package com.sample.post_service.service.impl;

import com.sample.post_service.entity.Media;
import com.sample.post_service.repository.MediaRepository;
import com.sample.post_service.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Media createMedia(Media media) {
        return mediaRepository.save(media);
    }

    @Override
    public Optional<Media> findById(String id) {
        return mediaRepository.findById(id);
    }

    @Override
    public List<Media> findByUserId(UUID userId) {
        return mediaRepository.findByUserId(userId);
    }

    @Override
    public void deleteMedia(String id) {
        if (!mediaRepository.existsById(id)) {
            throw new IllegalArgumentException("Media with ID " + id + " does not exist.");
        }
        mediaRepository.deleteById(id);
    }
}
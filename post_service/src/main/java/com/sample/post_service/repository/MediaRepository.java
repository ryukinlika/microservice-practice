package com.sample.post_service.repository;

import com.sample.post_service.entity.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MediaRepository extends MongoRepository<Media, UUID> {
    List<Media> findByUserId(UUID userId);
}
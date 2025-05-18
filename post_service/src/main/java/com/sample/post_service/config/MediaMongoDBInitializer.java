package com.sample.post_service.config;

import com.sample.post_service.entity.Media;
import com.sample.post_service.repository.MediaRepository;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MediaMongoDBInitializer {

    @Bean
    public CommandLineRunner populateMongoDB(MediaRepository mediaRepository, MongoTemplate mongoTemplate) {
        return args -> {
            // Check if the initialization flag exists
            if (!mongoTemplate.collectionExists("initialization_flag")) {
                // Insert initial data
                // Media(user, blobContent)
                mediaRepository.save(new Media(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), "Hello World".getBytes())); // username a
                mediaRepository.save(new Media(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"), "B1 World".getBytes())); // username b
                mediaRepository.save(new Media(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"), "B2 World".getBytes())); // username b
                mediaRepository.save(new Media(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"), "B3 World".getBytes())); // username b
                mediaRepository.save(new Media(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"), "Sample Data".getBytes())); // username b

                // Create the initialization flag
                mongoTemplate.createCollection("initialization_flag");
            }
        };
    }
}
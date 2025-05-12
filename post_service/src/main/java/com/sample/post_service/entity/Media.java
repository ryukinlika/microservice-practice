package com.sample.post_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "media")
public class Media {

    @Id
    private UUID id;
    private UUID userId;
    private byte[] blobContent;
    private LocalDateTime createdAt;

    // Constructors
    public Media() {
    }

    public Media(UUID userId, byte[] blobContent) {
        this.id = UUID.randomUUID(); // Automatically generate a UUID for the ID
        this.userId = userId;
        this.blobContent = blobContent;
        this.createdAt = LocalDateTime.now(); // Automatically set the creation time
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public byte[] getBlobContent() {
        return blobContent;
    }

    public void setBlobContent(byte[] blobContent) {
        this.blobContent = blobContent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
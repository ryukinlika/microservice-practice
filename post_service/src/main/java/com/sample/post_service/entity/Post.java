package com.sample.post_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "text_content", nullable = false, length = 500)
    private String textContent;

    @Column(name = "media_ids")
    private String mediaIds;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructor
    public Post(UUID userId, String textContent, String mediaIds) {
        this.userId = userId;
        this.textContent = textContent;
        this.mediaIds = mediaIds;
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

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(String mediaIds) {
        this.mediaIds = mediaIds;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
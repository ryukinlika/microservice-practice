package com.sample.timeline_service.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelinePost {

    private UUID postId;
    private UUID ownerId;
    private String textContent;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String[] mediaLinks;

    // default no media Links
    public TimelinePost(UUID postId, UUID ownerId, String textContent) {
        this.postId = postId;
        this.ownerId = ownerId;
        this.textContent = textContent;
    }
    // default with media Links
    public TimelinePost(UUID postId, UUID ownerId, String textContent, String[] mediaLinks) {
        this.postId = postId;
        this.ownerId = ownerId;
        this.textContent = textContent;
        this.mediaLinks = mediaLinks;
    }
}
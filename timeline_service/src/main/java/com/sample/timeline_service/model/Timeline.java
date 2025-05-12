package com.sample.timeline_service.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {

    private UUID timelineOwner;
    private List<TimelinePost> posts;
    private LocalDateTime createdAt = LocalDateTime.now();

    // default constructor
    public Timeline(UUID timelineOwner, List<TimelinePost> posts) {
        this.timelineOwner = timelineOwner;
        this.posts = posts;
    }
}
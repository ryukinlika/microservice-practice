package com.sample.timeline_service.service;

import com.sample.timeline_service.model.Timeline;

import java.util.UUID;

public interface TimelineService {

    // planned for future use
    // @Nullable
    // Timeline getTimelineFromCache(UUID id);

    Timeline getTimeline(UUID id);
}
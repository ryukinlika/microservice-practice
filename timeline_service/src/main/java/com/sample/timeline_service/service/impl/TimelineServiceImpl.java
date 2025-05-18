package com.sample.timeline_service.service.impl;

import com.sample.timeline_service.controller.client.PostClient;
import com.sample.timeline_service.controller.client.UserClient;
import com.sample.timeline_service.controller.client.model.FollowersModel;
import com.sample.timeline_service.controller.client.model.PostModel;
import com.sample.timeline_service.model.Timeline;
import com.sample.timeline_service.model.TimelinePost;
import com.sample.timeline_service.service.TimelineService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TimelineServiceImpl implements TimelineService {

    private final UserClient userClient;
    private final PostClient postClient;

    Logger logger = LoggerFactory.getLogger(TimelineServiceImpl.class);

    // Constructor-based Dependency Injection
    public TimelineServiceImpl(UserClient userClient, PostClient postClient) {
        this.userClient = userClient;
        this.postClient = postClient;
    }

    @Override
    public Timeline getTimeline(UUID id) {
        // Fetch following using UserClient
        Iterable<FollowersModel> following = userClient.getFollowing(id.toString());
        logger.info("following: {}", following);

        // List to store TimelinePost objects
        List<TimelinePost> posts = new ArrayList<>();

        // For each follower, fetch their posts and transform into TimelinePost
        for (FollowersModel follower : following) {
            if (follower.getFollowedId() != null) {
                logger.info("Follower ID: {}", follower.getFollowedId());
                try {
                    // Fetch posts by the followed user
                    Iterable<PostModel> userPosts = postClient.getPostsByUserId(follower.getFollowedId());
                    logger.info("User Posts: {}", userPosts);
                    for (PostModel post : userPosts) {
                        String[] mediaLinks = {};
                        // Transform mediaIds into an array of "/media/UUIDs"
                        if (post.getMediaIds() != null && !post.getMediaIds().isEmpty()) {
                            mediaLinks = post.getMediaIds().split(",");
                            for (int i = 0; i < mediaLinks.length; i++) {
                                mediaLinks[i] = "/media/" + mediaLinks[i];
                            }
                        }
    
                        // Add the transformed post to the TimelinePost list
                        posts.add(new TimelinePost(
                            post.getId(),                // postId
                            post.getUserId(),            // ownerId
                            post.getTextContent(),       // textContent
                            post.getCreatedAt(),         // createdAt
                            mediaLinks                   // mediaLinks
                        ));
                    }
                } catch (Exception e) {
                    logger.error("Error fetching posts for user ID {}: {}", follower.getFollowedId(), e.getMessage());
                    continue; // Skip to the next follower if there's an error
                }
                
            }
        }

        // Return the timeline
        return new Timeline(id, posts);
    }
}
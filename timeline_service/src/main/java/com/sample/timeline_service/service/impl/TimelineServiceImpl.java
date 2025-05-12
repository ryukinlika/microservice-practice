package com.sample.timeline_service.service.impl;

import com.sample.timeline_service.controller.client.PostClient;
import com.sample.timeline_service.controller.client.UserClient;
import com.sample.timeline_service.controller.client.model.FollowersModel;
import com.sample.timeline_service.controller.client.model.PostModel;
import com.sample.timeline_service.model.Timeline;
import com.sample.timeline_service.model.TimelinePost;
import com.sample.timeline_service.service.TimelineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TimelineServiceImpl implements TimelineService {

    private final UserClient userClient;
    private final PostClient postClient;

    // Constructor-based Dependency Injection
    public TimelineServiceImpl(UserClient userClient, PostClient postClient) {
        this.userClient = userClient;
        this.postClient = postClient;
    }

    @Override
    public Timeline getTimeline(UUID id) {
        // Fetch followers using UserClient
        Iterable<FollowersModel> followers = userClient.getFollowers(id);

        // List to store TimelinePost objects
        List<TimelinePost> posts = new ArrayList<>();

        // For each follower, fetch their posts and transform into TimelinePost
        for (FollowersModel follower : followers) {
            if (follower.getFollowingId() != null) {
                Iterable<PostModel> userPosts = postClient.getPostsByUserId(follower.getFollowingId());
                for (PostModel post : userPosts) {
                    // Transform mediaIds into an array of "/media/UUIDs"
                    String[] mediaLinks = post.getMediaIds() != null
                        ? post.getMediaIds().split(",")
                        : new String[]{};

                    for (int i = 0; i < mediaLinks.length; i++) {
                        mediaLinks[i] = "/media/" + mediaLinks[i];
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
            }
        }

        // Return the timeline
        return new Timeline(id, posts);
    }
}
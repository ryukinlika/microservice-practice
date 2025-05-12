package com.sample.user_service.service;

import java.util.List;
import java.util.UUID;

import com.sample.user_service.entity.Relationship;
import com.sample.user_service.entity.RelationshipId;

public interface RelationshipService {
    Relationship save(Relationship user);

    boolean unfollow(RelationshipId rID);
    
    List<Relationship> findFollowers(UUID followerID);
    List<Relationship> findFollowings(UUID followerID);
}

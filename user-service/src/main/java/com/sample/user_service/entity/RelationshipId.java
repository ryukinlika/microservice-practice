package com.sample.user_service.entity;

import java.io.Serializable;
import java.util.UUID;

public class RelationshipId implements Serializable {
    public UUID followedId;
    public UUID followerId;

    // default constructor

    public RelationshipId(UUID followerId, UUID followedId) {
        this.followedId = followedId;
        this.followerId = followerId;
    }

    // equals() and hashCode()
}

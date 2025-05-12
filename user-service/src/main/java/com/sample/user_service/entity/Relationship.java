package com.sample.user_service.entity;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(RelationshipId.class)
public class Relationship {

    @Id
    UUID followedId;
    @Id
    UUID followerId;
  
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date created_at;

    public Relationship(RelationshipId rId){
        this.followedId = rId.followedId;
        this.followerId = rId.followerId;
        created_at = new Date(System.currentTimeMillis());
    }

    public Relationship(UUID follower, UUID followed){
        this.followedId = followed;
        this.followerId = follower;
        created_at = new Date(System.currentTimeMillis());
    }

    // GETTER SETTERS
    public UUID getFollowerId(){
        return followerId;
    }
    public UUID getFollowedId(){
        return followedId;
    }
    public Date getCreated(){
        return created_at;
    }

    // public void setFollowerId(UUID id){
    //     followerId = id;
    // }
    // public void setFollowedId(UUID id){
    //     followedId = id;
    // }
}

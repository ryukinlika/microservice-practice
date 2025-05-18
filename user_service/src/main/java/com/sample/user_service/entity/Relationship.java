package com.sample.user_service.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@IdClass(RelationshipId.class)
public class Relationship implements Serializable{

    @Id
    UUID followedId;
    @Id
    UUID followerId;
  
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonSerialize(using=LocalDateTimeSerializer.class)
    @JsonDeserialize(using=LocalDateTimeDeserializer.class)
    LocalDateTime created_at;

    public Relationship(RelationshipId rId){
        this.followedId = rId.followedId;
        this.followerId = rId.followerId;
        created_at = LocalDateTime.now();
    }

    public Relationship(UUID follower, UUID followed){
        this.followedId = followed;
        this.followerId = follower;
        created_at = LocalDateTime.now();
    }

}

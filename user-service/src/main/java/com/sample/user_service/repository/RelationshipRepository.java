package com.sample.user_service.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.user_service.entity.Relationship;
import com.sample.user_service.entity.RelationshipId;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;



public interface RelationshipRepository extends CrudRepository<Relationship, RelationshipId>{
    @Query("SELECT r FROM Relationship r WHERE r.followerId = :followerId")
    List<Relationship> findByFollowerId(UUID followerId);

    @Query("SELECT r FROM Relationship r WHERE r.followedId = :followedId")
    List<Relationship> findByFollowedId(UUID followerId);
}

package com.sample.user_service.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.user_service.entity.Relationship;
import com.sample.user_service.entity.RelationshipId;
import com.sample.user_service.repository.RelationshipRepository;
import com.sample.user_service.service.RelationshipService;

import jakarta.transaction.Transactional;

@Service
public class RelationshipServiceImpl implements RelationshipService{
    @Autowired
    RelationshipRepository relRepo;

    Logger logger = LoggerFactory.getLogger(RelationshipServiceImpl.class);


    @Override
    @Transactional
    public Relationship save(Relationship rel){

        return relRepo.save(rel);
    }

    @Override
    @Transactional
    public boolean unfollow(RelationshipId rId){

        if(!relRepo.existsById(rId)){
            logger.error("not followed in the first place!");
            throw new RuntimeException("invalid input");
        };

        relRepo.deleteById(rId);

        return true;
    }

    @Override
    public List<Relationship> findFollowers(UUID followed){
        return relRepo.findByFollowedId(followed);   
    }
    
    @Override
    public List<Relationship> findFollowings(UUID follower){
        return relRepo.findByFollowerId(follower);
    }
}

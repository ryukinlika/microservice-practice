package com.sample.user_service.controller;

import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.user_service.entity.Relationship;
import com.sample.user_service.entity.RelationshipId;
import com.sample.user_service.service.RelationshipService;

@Controller
@RequestMapping(path = "/relationship") // URL starts with /relationship after app path
public class RelationshipController {
    // Services
    @Autowired
    RelationshipService relationshipService;

    // Others
    Logger logger = LoggerFactory.getLogger(RelationshipController.class);

    
    @PostMapping(path="/follow")
    public @ResponseBody String followUser(@RequestParam String followed, @RequestParam String follower) throws BadRequestException{
        if (!isValidUUID(follower) || !isValidUUID(followed)){ 
            throw new BadRequestException("Invalid UUID format for follower: " + follower + " or followed " + followed);
        }
        if(followed.equals(follower)){
            logger.error("self follow!");
            throw new BadRequestException("path /relationship/follow API followUser - invalid input");
        }

        Relationship rel = new Relationship(new RelationshipId(UUID.fromString(followed), UUID.fromString(follower)));
        relationshipService.save(rel);

        return "OK";
    }

    @PostMapping(path="/unfollow")
    public @ResponseBody String unfollowUser(@RequestParam String followed, @RequestParam String follower) throws BadRequestException{
        if (!isValidUUID(follower) || !isValidUUID(followed)){ 
            throw new BadRequestException("Invalid UUID format for follower: " + follower + " or followed " + followed);
        }
        if(followed.equals(follower)){
            logger.error("self unfollow!");
            throw new BadRequestException("path /relationship/unfollow API unfollowUser - invalid input");
        }

        RelationshipId rId = new RelationshipId(UUID.fromString(follower), UUID.fromString(followed));
        relationshipService.unfollow(rId);

        return "OK";
    }


    @GetMapping(path="/{userid}/followers")
    public @ResponseBody Iterable<Relationship> getFollowers(@PathVariable(name="userid") String userUUID){
        logger.info("getFollower: userUUID: " + userUUID);
        return relationshipService.findFollowers(UUID.fromString(userUUID));
    }

    @GetMapping(path="/{userid}/following")
    public @ResponseBody Iterable<Relationship> getFollowing(@PathVariable(name="userid") String userUUID){
        logger.info("getFollowing: userUUID: " + userUUID);
        return relationshipService.findFollowings(UUID.fromString(userUUID));
    }

    private boolean isValidUUID(String uuid) {
        try {
            return UUID.fromString(uuid).toString().equals(uuid);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

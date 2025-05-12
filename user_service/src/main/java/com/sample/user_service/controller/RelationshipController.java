package com.sample.user_service.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public @ResponseBody String followUser(@RequestParam String followed, @RequestParam String follower){
        if (!followed.equals(UUID.fromString(followed).toString()) || !follower.equals(UUID.fromString(follower).toString())){
            throw new InvalidUserInputException("path /relationship/follow API followUser - invalid input");
        }
        if(followed.equals(follower)){
            throw new InvalidUserInputException("path /relationship/follow API followUser - invalid input");
        }

        Relationship rel = new Relationship(new RelationshipId(UUID.fromString(follower), UUID.fromString(followed)));
        relationshipService.save(rel);

        return "OK";
    }

    @PostMapping(path="/unfollow")
    public @ResponseBody String unfollowUser(@RequestParam String followed, @RequestParam String follower){
        if (!followed.equals(UUID.fromString(followed).toString()) || !follower.equals(UUID.fromString(follower).toString())){
            logger.error("invalid UUID");
            throw new InvalidUserInputException("path /relationship/unfollow API unfollowUser - invalid input");
        }
        if(followed.equals(follower)){
            logger.error("self unfollow!");
            throw new InvalidUserInputException("path /relationship/unfollow API unfollowUser - invalid input");
        }

        RelationshipId rId = new RelationshipId(UUID.fromString(follower), UUID.fromString(followed));
        relationshipService.unfollow(rId);

        return "OK";
    }


    @GetMapping(path="/{userid}/followers")
    public @ResponseBody Iterable<Relationship> getFollowers(@PathVariable(name="userid") String userUUID){
        return relationshipService.findFollowers(UUID.fromString(userUUID));
    }

    @GetMapping(path="/{userid}/following")
    public @ResponseBody Iterable<Relationship> getFollowing(@PathVariable(name="userid") String userUUID){
        return relationshipService.findFollowings(UUID.fromString(userUUID));

    }

     // EXCEPTION HANDLING
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid User API input")  // 400
    public class InvalidUserInputException extends RuntimeException {
        InvalidUserInputException(String message){
            super(message);
        }
    }
}

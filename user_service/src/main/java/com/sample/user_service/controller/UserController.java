package com.sample.user_service.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sample.user_service.entity.Relationship;
import com.sample.user_service.entity.RelationshipId;
import com.sample.user_service.entity.User;
import com.sample.user_service.service.RelationshipService;
import com.sample.user_service.service.UserService;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;




@Controller
@RequestMapping(path="/user") //url starts with /user after app path
public class UserController{
    // Services
    @Autowired
    UserService userService;
    // List<User> searchByUsername(String username);
    // User updateUser(UUID id, User newUserDetails);
    // void deleteUser(UUID id);
    @Autowired
    RelationshipService relationshipService;

    // Others
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path="/add") //only post
    public @ResponseBody String addNewUser (@RequestParam String username, @RequestParam String email, @RequestParam String password){
        username = username.trim();
        email = email.trim();
        if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
            logger.error("username, email, or password is empty");
            throw new InvalidUserInputException("path /user/add API addNewUser - invalid input");
        }

        User user = new User(username, email, password);
        userService.save(user);

        return "OK";
    }

    @GetMapping(path="/search") //only get
    public @ResponseBody List<User> searchByUsername(@RequestParam(name="username") String username){
        if(username.isEmpty()){
            logger.error("username is empty");
            throw new InvalidUserInputException("path /user/search API searchByUsername - invalid input");
        }
        return userService.searchByUsername(username);
    }


    @PutMapping(path="/update/{id}") 
    public @ResponseBody String updateUser(@PathVariable String id, @RequestParam String username, @RequestParam String email, @RequestParam String password){
        if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
            logger.error("username, email, or password is empty");
            throw new InvalidUserInputException("path /user/update/{id} API updateUser - invalid input");
        }
        UUID userId = UUID.fromString(id);
        if(!userId.toString().equals(id)){
            logger.error("invalid UUID");
            throw new InvalidUserInputException("path /user/update/{id} API updateUser - invalid input");
        }
        User user = new User(username, email, password);
        userService.updateUser(userId, user);

        return "OK";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUser(@PathVariable String id){
        UUID userId = UUID.fromString(id);
        if(!userId.toString().equals(id)){
            logger.error("invalid UUID");
            throw new InvalidUserInputException("path /user/update/{id} API updateUser - invalid input");
        }
        userService.deleteUser(userId);
        return "OK";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userService.getAllUsers(); //JSON or XML of all 
    }



    // EXCEPTION HANDLING
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid User API input")  // 400
    public class InvalidUserInputException extends RuntimeException {
        InvalidUserInputException(String message){
            super(message);
        }
    }

}
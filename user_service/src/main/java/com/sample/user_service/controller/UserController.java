package com.sample.user_service.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sample.user_service.entity.User;
import com.sample.user_service.service.RelationshipService;
import com.sample.user_service.service.UserService;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
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

    UserController() { }

    @PostMapping(path="/add") //only post
    public @ResponseBody String addNewUser (@RequestParam String username, @RequestParam String email, @RequestParam String password) throws BadRequestException{
        username = username.trim();
        email = email.trim();
        if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
            logger.error("username, email, or password is empty");
            throw new BadRequestException("path /user/add API addNewUser - invalid input");
        }

        User user = new User(username, email, password);
        userService.save(user);

        return "OK";
    }

    @GetMapping(path="/search") //only get
    public @ResponseBody List<User> searchByUsername(@RequestParam(name="username") String username) throws BadRequestException{
        if(username.isEmpty()){
            logger.error("username is empty");
            throw new BadRequestException("path /user/search API searchByUsername - invalid input");
        }
        return userService.searchByUsername(username);
    }


    @PutMapping(path="/update/{id}") 
    public @ResponseBody String updateUser(
            @PathVariable String id, 
            @RequestParam(required = false) String username, 
            @RequestParam(required = false) String email, 
            @RequestParam(required = false) String password) throws BadRequestException {
        if ((username == null || username.isEmpty()) && 
            (email == null || email.isEmpty()) && 
            (password == null || password.isEmpty())) {
            logger.error("username, email, and password is empty");
            throw new BadRequestException("path /user/update/{id} API updateUser - invalid input");
        }

        UUID userId = UUID.fromString(id);
        if (!userId.toString().equals(id)) {
            logger.error("invalid UUID");
            throw new BadRequestException("path /user/update/{id} API updateUser - invalid input");
        }

        User oldUser = userService.getUserById(userId);
        if (oldUser == null) {
            logger.error("user not found");
            throw new BadRequestException("path /user/update/{id} API updateUser - invalid input");
        }

        if (username != null && !username.isEmpty()) {
            oldUser.setUsername(username);
        }
        if (email != null && !email.isEmpty()) {
            oldUser.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            oldUser.setPassword(password);
        }
        userService.updateUser(userId, oldUser);

        return "OK";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody void deleteUser(@PathVariable String id) throws BadRequestException{
        UUID userId = UUID.fromString(id);
        if(!userId.toString().equals(id)){
            logger.error("invalid UUID");
            throw new BadRequestException("path /user/update/{id} API updateUser - invalid input");
        }

        try {
            userService.deleteUser(userId);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new BadRequestException("path /user/delete/{id} API deleteUser - invalid input");
        }
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userService.getAllUsers(); //JSON or XML of all 
    }

    @GetMapping(path="/{id}")
    public @ResponseBody User getUserById(@PathVariable String id) throws InputNotFoundException, BadRequestException{
        UUID userId = UUID.fromString(id);
        if(!userId.toString().equals(id)){
            logger.error("invalid UUID");
            throw new BadRequestException("path /user/{id} API getUserById - invalid input");
        }
        
        User ret =  userService.getUserById(userId);
        if (ret  == null) {
            logger.error("user not found, userId: " + userId);
            ret = new User();
            throw new InputNotFoundException("path /user/{id} API getUserById - invalid input");
        }
        return ret;
    }


    // EXCEPTION HANDLING
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Data not found")  // 403
    public class InputNotFoundException extends Exception {
        InputNotFoundException(String message){
            super(message);
        }
    }

}
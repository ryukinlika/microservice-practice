package com.sample.user_service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.user_service.entity.User;
import com.sample.user_service.repository.UserRepository;
import com.sample.user_service.service.UserService;

import jakarta.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public User save(User user){
        Optional<User> isExist = Optional.of(userRepository.findByUsername(user.getUsername()))
        .or(()-> Optional.of(userRepository.findByEmail(user.getEmail())))
        .orElse(null);

        if(isExist.isPresent())
            throw new RuntimeException("already exists");
        
        return userRepository.save(user);
    }

    @Override
    public List<User> searchByUsername(String username){
        List<User> users = userRepository.findByUsernameContaining(username);
        if(users.isEmpty())
            throw new RuntimeException("not found");
        return users;
    }

    @Override
    public User updateUser(UUID id, User newUserDetails){
        User userExist = userRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("updating id" + id + " not found"));

        if(newUserDetails.getEmail() != null && !newUserDetails.getEmail().isEmpty()){
            userExist.setEmail(newUserDetails.getEmail());
        }
        if(newUserDetails.getUsername() != null && !newUserDetails.getUsername().isEmpty()){
            userExist.setUsername(newUserDetails.getUsername());
        }   
        if(newUserDetails.getPassword() != null && !newUserDetails.getPassword().isEmpty()){
            userExist.setPassword(newUserDetails.getPassword());
        }   
        // userExist.setAdmin(newUserDetails.getAdmin());
        return userRepository.save(userExist);
    }

    @Override
    public void deleteUser(UUID id){
        User userExist = userRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("deleting id " + id + " not found"));
        
        logger.info("deleting UUID: " + id + " username: " + userExist.getUsername() + " email: " + userExist.getEmail());
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
}

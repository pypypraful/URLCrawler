package com.vipra.Demo.controller;

import com.vipra.Demo.models.User;
//import com.vipra.Demo.mongodb.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

//    private final UserRepository userRepository;
//
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<User> getAllUsers() {
//        LOG.info("Getting all users.");
//        return userRepository.findAll();
//    }

//    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
//    public User getUser(@PathVariable String userId) {
//        LOG.info("Getting user with ID: {}.", userId);
//        return userRepository.findOne(userId);
//    }

}

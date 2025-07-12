package com.newproject.journalapp.controller;

import com.newproject.journalapp.entity.User;
import com.newproject.journalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser(@RequestBody User newUser){
        userService.saveUser(newUser);
        return "User created successfully";
    }


}

package com.newproject.journalapp.controller;

import com.newproject.journalapp.entity.User;
import com.newproject.journalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    @GetMapping
    public Optional<User> getUserByUsername(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        return userService.getUserByUsername(username);
    }

    @DeleteMapping
    public String deleteUserById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userService.deleteUserByUsername(userName);
        return "User Deleted";
    }

    @PutMapping
    public String updateUserById(@RequestBody User updatedUser){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User old=userService.getUserByUsername(userName).orElse(null);
        if(old!=null){
            old.setUsername( !updatedUser.getUsername().isEmpty()? updatedUser.getUsername():old.getUsername());
            old.setPassword( !updatedUser.getPassword().isEmpty()? updatedUser.getPassword():old.getPassword());
            userService.saveUser(old);
            return "User Updated";
        }
        else {
            return "No User Exists ";
        }
    }
}

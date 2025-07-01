package com.newproject.journalapp.controller;

import com.newproject.journalapp.entity.User;
import com.newproject.journalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUser();
    }

    @PostMapping
    public String createUser(@RequestBody User newUser){
        userService.saveUser(newUser);
        return "User created successfully";
    }

    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return "User Deleted";
    }

    @PutMapping("/{id}")
    public String updateUserById(@PathVariable String id,@RequestBody User updatedUser){
        User old= userService.getUserById(id).orElse(null);
        if(old!=null){
            old.setUsername( updatedUser.getUsername()!=""? updatedUser.getUsername():old.getUsername());
            old.setPassword( updatedUser.getPassword()!=""? updatedUser.getPassword():old.getPassword());
            userService.saveUser(old);
            return "User Updated";
        }
        else {
            return "No User Exists ";
        }
    }
}

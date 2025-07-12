package com.newproject.journalapp.services;

import com.newproject.journalapp.entity.User;
import com.newproject.journalapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Post logic
    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    //Get Logic
    public List<User> getUser(){
        return userRepository.findAll();
    }

    //Get Logic by username
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional <User> getUserById(String Id){
        return userRepository.findById(Id);
    }

    //Delete Logic
    public void deleteUserById(String id){
        userRepository.deleteById(id);

    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUsername(username);
    }
}

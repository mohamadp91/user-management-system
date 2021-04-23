package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.model.UserModel;
import com.example.usermanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<UserModel> getUserById(@PathVariable long id) {
        return userRepository.findById(id);
    }

    @PostMapping(value = "/users")
    public UserModel addUser(@RequestBody UserModel user) {

        Date date = new Date();

        String creationTime = date.toString();
        UserModel userModel = new UserModel(
                user.getFirstName(),
                user.getLastName(),
                creationTime,
                user.getEmailAddress());

        userRepository.save(userModel);
        return userModel;
    }

    @DeleteMapping("/users/{id}")
    public Optional<UserModel> deleteUser(@PathVariable long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        userRepository.deleteById(id);
        return userModel;
    }

}

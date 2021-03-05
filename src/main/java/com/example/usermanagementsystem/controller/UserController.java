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

    @GetMapping("/user")
    public Optional<UserModel> getUserById(@RequestParam String id) {
        return userRepository.findById(id);
    }

    @PostMapping(value = "/users")
    public UserModel addUser(@RequestParam String id, @RequestParam String name, @RequestParam String emailAddress) {
        Date date = new Date();
        String dataCreated = date.toString();
        UserModel userModel = new UserModel(id, name, dataCreated, emailAddress);
        userRepository.save(userModel);
        return userModel;
    }

    @DeleteMapping("/users")
    public Optional<UserModel> deleteUser(@RequestParam String id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        userRepository.deleteById(id);
        return userModel;
    }

}

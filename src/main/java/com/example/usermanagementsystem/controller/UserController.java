package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.model.UserModel;
import com.example.usermanagementsystem.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Value("DATABASE_IS_DISCONNECT")
    private String DATABASE_IS_DISCONNECT;

    @GetMapping("/check_connection")
    @CircuitBreaker(name = "main", fallbackMethod = "checkFallback")
    public String checkConnection() {
        userRepository.findAll();
        return "alive";
    }

    public String checkFallback(Throwable t) {
        return DATABASE_IS_DISCONNECT;
    }

    @GetMapping("/users")
    @CircuitBreaker(name = "main", fallbackMethod = "getAllUsersFallback")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserModel> getAllUsersFallback(Throwable t) {
        ArrayList<UserModel> userList = new ArrayList<>();
        userList.add(new UserModel());
        return userList;
    }

    @GetMapping("/users/{id}")
    @CircuitBreaker(name = "main", fallbackMethod = "getUserFallback")
    public UserModel getUserById(@PathVariable long id) {
        return userRepository.getUserModelById(id);
    }

    public UserModel getUserFallback(Throwable t) {
        return new UserModel();
    }

    @PostMapping(value = "/users")
    @CircuitBreaker(name = "main", fallbackMethod = "addUserFallback")
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

    public UserModel addUserFallback(Throwable t) {
        return new UserModel();
    }

    @DeleteMapping("/users/{id}")
    @CircuitBreaker(name = "main", fallbackMethod = "deleteUserFallback")
    public UserModel deleteUser(@PathVariable long id) {
        UserModel userModel = userRepository.getUserModelById(id);
        userRepository.deleteById(id);
        return userModel;
    }

    public UserModel deleteUserFallback(Throwable t) {
        return new UserModel();
    }

}

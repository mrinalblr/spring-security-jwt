package com.stackfortech.springsecurityjwt.controller;

import com.stackfortech.springsecurityjwt.entity.User;
import com.stackfortech.springsecurityjwt.repository.UserRepository;
import com.stackfortech.springsecurityjwt.response.UserResponse;
import com.stackfortech.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;



    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody User user){
       return userService.registerUser(user);
    }
    @GetMapping("/search")
    public ResponseEntity<UserResponse> getUsers(@AuthenticationPrincipal User user){
        return userService.findAll();
    }
    @GetMapping("/searchBy")
    public ResponseEntity<UserResponse> getUsers(@RequestParam String username){
        return userService.getUsers(username);
    }
    @PutMapping("/updatePassword")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody User user){
        return userService.updatePassword(user);
    }

}

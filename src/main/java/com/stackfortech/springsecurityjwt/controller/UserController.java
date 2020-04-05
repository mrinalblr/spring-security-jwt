package com.stackfortech.springsecurityjwt.controller;

import com.stackfortech.springsecurityjwt.entity.User;
import com.stackfortech.springsecurityjwt.repository.UserRepository;
import com.stackfortech.springsecurityjwt.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<UserResponse>(new UserResponse("success","User successfully created",null),HttpStatus.CREATED);
    }
    @GetMapping("/search")
    public ResponseEntity<UserResponse> getUsers(@AuthenticationPrincipal User user){

        List<User> userList = userRepository.findAll();
        UserResponse response = new UserResponse();
        if(!userList.isEmpty()){

            response.setStatus("success");
            response.setData(userList);
        }else{
            response.setStatus("failure");
            response.setMessage("No data found");
            response.setData(new ArrayList<User>());
        }

        return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
    }
    @GetMapping("/searchBy")
    public ResponseEntity<UserResponse> getUsers(@RequestParam String username){
        User user =  userRepository.findByUsername(username);
        UserResponse response = new UserResponse();
        if(user!=null){
            response.setStatus("success");
            response.setData(user);
        }else{
            response.setStatus("failure");
            response.setMessage("No such username exists");
        }
        ResponseEntity<UserResponse> responseResponseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseResponseEntity;
    }
}

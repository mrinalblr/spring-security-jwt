package com.stackfortech.springsecurityjwt.service;

import com.stackfortech.springsecurityjwt.entity.User;
import com.stackfortech.springsecurityjwt.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

@Service
public interface UserService {
    public ResponseEntity<UserResponse> findAll();
    public ResponseEntity<UserResponse> registerUser(User user);
    public ResponseEntity<UserResponse> getUsers(String username);
    public ResponseEntity<UserResponse> updatePassword(User user);
}

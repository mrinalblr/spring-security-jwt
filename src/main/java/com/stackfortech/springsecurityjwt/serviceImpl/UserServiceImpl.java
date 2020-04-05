package com.stackfortech.springsecurityjwt.serviceImpl;

import com.stackfortech.springsecurityjwt.entity.User;
import com.stackfortech.springsecurityjwt.repository.UserRepository;
import com.stackfortech.springsecurityjwt.response.UserResponse;
import com.stackfortech.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<UserResponse> findAll() {
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
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<UserResponse>(new UserResponse("success","User successfully created",null),HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<UserResponse> getUsers(String username) {
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

    @Override
    public ResponseEntity<UserResponse> updatePassword(User user) {
        User retrievedUser = userRepository.findByUsername(user.getUsername());
        retrievedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.updatePassword(retrievedUser.getPassword(),retrievedUser.getId());
        return new ResponseEntity<UserResponse>(new UserResponse("success","password updated successfully",null),HttpStatus.CREATED);
    }
}

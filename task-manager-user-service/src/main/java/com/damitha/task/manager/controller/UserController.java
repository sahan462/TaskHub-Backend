package com.damitha.task.manager.controller;

import com.damitha.task.manager.model.User;
import com.damitha.task.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //ResponseEntity<User> => this indicates that the body of the response is an object of type user
    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getUserProfile(@PathVariable String email){
        User user=userService.getUserProfile(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String jwt){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}

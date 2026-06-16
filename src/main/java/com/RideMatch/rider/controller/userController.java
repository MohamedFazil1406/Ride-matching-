package com.RideMatch.rider.controller;


import com.RideMatch.rider.entity.User;
import com.RideMatch.rider.service.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController{

    private final userService userService;


    public userController(userService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        User registerUser = userService.register(user);

        if(registerUser == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
        }
        return ResponseEntity.ok("User Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        String token = userService.login(user);

        if(token == null){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
        return ResponseEntity.ok(token);
    }

}


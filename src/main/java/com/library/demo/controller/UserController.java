package com.library.demo.controller;

import com.library.demo.DTO.UserRequest;
import com.library.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest){
        userService.saveUser(userRequest);
        return ResponseEntity.ok("User saved succesfully");
    }

    @GetMapping("/login")
    public ResponseEntity<String> deneme(){

        return ResponseEntity.ok("çalışıyor");
    }
}

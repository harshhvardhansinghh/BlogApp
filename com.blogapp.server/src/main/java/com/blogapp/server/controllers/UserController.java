package com.blogapp.server.controllers;

import com.blogapp.server.entities.User;
import com.blogapp.server.payloads.UserDto;
import com.blogapp.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //POST-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createUserDto= this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //PUT- update user

    //DELETE- delete user

    //GET-user get

}

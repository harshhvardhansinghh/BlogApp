package com.blogapp.server.controllers;

import com.blogapp.server.entities.User;
import com.blogapp.server.payloads.ApiResponse;
import com.blogapp.server.payloads.UserDto;
import com.blogapp.server.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST-create user

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto= this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }


    //PUT- update user

    @PutMapping("/{userId}") //path uri variable
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid)
    {
        UserDto updatedUser =this.userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE- delete user

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
    {
        this.userService.deleteUser(uid);
        return new ResponseEntity(new ApiResponse("User Delete Successfully",true), HttpStatus.OK);
    }

    //GET-user get

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //GET - show user by user id

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getAllUsers(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}

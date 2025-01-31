package com.blogapp.server.services.impl;

import com.blogapp.server.config.AppConstants;
import com.blogapp.server.entities.Role;
import com.blogapp.server.entities.User;
import com.blogapp.server.exceptions.ResourceNotFoundException;
import com.blogapp.server.payloads.UserDto;
import com.blogapp.server.repositories.RoleRepo;
import com.blogapp.server.repositories.UserRepo;
import com.blogapp.server.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDto registerNewUser(UserDto userDto) {
        // Convert UserDto to User
        User user = this.modelMapper.map(userDto, User.class);

        // Encode the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // Get the role and ensure it is present
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", AppConstants.NORMAL_USER));

        // Add role to user
        user.getRoles().add(role);

        // Save the new user
        User newUser = this.userRepo.save(user);

        // Convert User to UserDto and return
        return this.modelMapper.map(newUser, UserDto.class);
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User>users= this.userRepo.findAll();
        List<UserDto>userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);

    }

    public User dtoToUser(UserDto userDto)
    {
        User user=this.modelMapper.map(userDto, User.class);
        /*
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;
         */
        return user;
    }

    public UserDto userToDto (User user)
    {
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
        /*
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(userDto.getPassword());
        return userDto;
         */
        return userDto;
    }
}

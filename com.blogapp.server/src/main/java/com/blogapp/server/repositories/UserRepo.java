package com.blogapp.server.repositories;

import com.blogapp.server.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}

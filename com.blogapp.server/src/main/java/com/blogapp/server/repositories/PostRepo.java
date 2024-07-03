package com.blogapp.server.repositories;

import com.blogapp.server.entities.Category;
import com.blogapp.server.entities.Post;
import com.blogapp.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    //find all post of a specific user
    List<Post> findByUser(User user);

    //find all post of a category
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}

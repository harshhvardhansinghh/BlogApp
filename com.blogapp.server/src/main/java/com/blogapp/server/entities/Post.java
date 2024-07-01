package com.blogapp.server.entities;

import jakarta.persistence.*;
import org.hibernate.Length;

import java.util.Date;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name="post_title", length = 100, nullable = false)
    private String title;

    @Column(length=10000)
    private String content;

    private String imageName;

    private Date addedDate;

    //relationship among category and posts
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //relationship among user and posts
    @ManyToOne
    private User user;
}

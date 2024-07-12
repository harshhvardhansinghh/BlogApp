package com.blogapp.server.services;

import com.blogapp.server.entities.Comment;
import com.blogapp.server.payloads.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto, Integer postId);

    public void deleteComment(Integer commentId);
}

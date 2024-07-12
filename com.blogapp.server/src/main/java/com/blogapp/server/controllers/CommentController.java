package com.blogapp.server.controllers;


import com.blogapp.server.entities.Comment;
import com.blogapp.server.payloads.ApiResponse;
import com.blogapp.server.payloads.CommentDto;
import com.blogapp.server.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId)
    {
        CommentDto createComment= this.commentService.createComment(comment,postId);
        return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
    {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted successfully !", true),HttpStatus.OK);
    }
}

package com.backendproject.PostBook_App_API.Controllers;

import com.backendproject.PostBook_App_API.Entities.User;
import com.backendproject.PostBook_App_API.Payloads.ApiResponse;
import com.backendproject.PostBook_App_API.Payloads.CommentDto;
import com.backendproject.PostBook_App_API.Services.CommentService;
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
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comDto,
                                                    @RequestAttribute("loginUser") User loginUser,
                                                    @PathVariable Integer postId) {

        CommentDto createComment = this.commentService.createComment(comDto, postId, loginUser.getId());
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);

    }
}

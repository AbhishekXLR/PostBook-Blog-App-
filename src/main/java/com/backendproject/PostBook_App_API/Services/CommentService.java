package com.backendproject.PostBook_App_API.Services;

import com.backendproject.PostBook_App_API.Payloads.CommentDto;


public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

    void deleteComment(Integer commentId);
}

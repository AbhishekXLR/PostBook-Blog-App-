package com.backendproject.PostBook_App_API.Services.impl;

import com.backendproject.PostBook_App_API.Entities.Comment;
import com.backendproject.PostBook_App_API.Entities.Post;
import com.backendproject.PostBook_App_API.Entities.User;
import com.backendproject.PostBook_App_API.Exceptions.ResourceNotFoundException;
import com.backendproject.PostBook_App_API.Payloads.CommentDto;
import com.backendproject.PostBook_App_API.Repositories.CommentRepo;
import com.backendproject.PostBook_App_API.Repositories.PostRepo;
import com.backendproject.PostBook_App_API.Repositories.UserRepo;
import com.backendproject.PostBook_App_API.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);

        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);

    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment com = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepo.delete(com);
    }
}

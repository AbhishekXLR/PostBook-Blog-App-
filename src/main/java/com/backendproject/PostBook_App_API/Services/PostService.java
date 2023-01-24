package com.backendproject.PostBook_App_API.Services;

import com.backendproject.PostBook_App_API.Payloads.PostDto;
import com.backendproject.PostBook_App_API.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy);

    PostDto getPostById(Integer postId);


    // get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all post by user.
    List<PostDto> getPostsByUser(Integer userId);

    // search post
    List<PostDto> searchPosts(String keyword);
}

package com.backendproject.PostBook_App_API.Services.impl;

import com.backendproject.PostBook_App_API.Entities.Category;
import com.backendproject.PostBook_App_API.Entities.Post;
import com.backendproject.PostBook_App_API.Entities.User;
import com.backendproject.PostBook_App_API.Exceptions.ResourceNotFoundException;
import com.backendproject.PostBook_App_API.Payloads.PostDto;
import com.backendproject.PostBook_App_API.Payloads.PostResponse;
import com.backendproject.PostBook_App_API.Repositories.CategoryRepo;
import com.backendproject.PostBook_App_API.Repositories.PostRepo;
import com.backendproject.PostBook_App_API.Repositories.UserRepo;
import com.backendproject.PostBook_App_API.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        Post createdPost = this.modelMapper.map(postDto, Post.class);
        createdPost.setImageName("default.png");
        createdPost.setAddedDate(new Date());
        createdPost.setUser(user);
        createdPost.setCategory(category);

        Post newPost = this.postRepo.save(createdPost);
        PostDto newPostDto = this.modelMapper.map(newPost, PostDto.class);
        return newPostDto;

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);

    }


    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        this.postRepo.delete(post);
    }


    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

        // For Paging

        Pageable p = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());  //Sort class
        Page<Post> pageOfPosts = this.postRepo.findAll(p);
        List<Post> allPosts = pageOfPosts.getContent();

        List<PostDto> allPostDto = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDto);
        postResponse.setPageNumber(pageOfPosts.getNumber());
        postResponse.setPageSize(pageOfPosts.getSize());
        postResponse.setTotalElements(pageOfPosts.getTotalElements());
        postResponse.setTotalPages(pageOfPosts.getTotalPages());
        postResponse.setLastPage(pageOfPosts.isLast());

        return postResponse;
    }


    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }


    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        List<Post> postsByCategory = this.postRepo.findByCategory(category);

        //as we can't convert List of PostsDto using modelMapper. So, we need to fetch single-2 post FROM  LIST OF POST and convert them into List of PostDto
        List<PostDto> postDtos = postsByCategory.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }


    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        List<Post> postsOfUser = this.postRepo.findByUser(user);

        List<PostDto> postDtos = postsOfUser.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }


    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}

package com.backendproject.PostBook_App_API.Controllers;

import com.backendproject.PostBook_App_API.Config.AppConstants;
import com.backendproject.PostBook_App_API.Payloads.ApiResponse;
import com.backendproject.PostBook_App_API.Payloads.PostDto;
import com.backendproject.PostBook_App_API.Payloads.PostResponse;
import com.backendproject.PostBook_App_API.Services.Fileservice;
import com.backendproject.PostBook_App_API.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private Fileservice fileservice;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // get by user

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getUserPost(@PathVariable Integer userId) {
        List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postsByUser, HttpStatus.OK);
    }

    // get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getCategoryPost(@PathVariable Integer categoryId) {
        List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postsByCategory, HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumberl,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
        PostResponse allPost = this.postService.getAllPost(pageNumberl, pageSize, sortBy);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    //get single posts
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postById = this.postService.getPostById(postId);
        return new ResponseEntity<>(postById, HttpStatus.ACCEPTED);
    }

    // delete post by postId
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Your Post is Successfully deleted", true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto postUpdated = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postUpdated, HttpStatus.ACCEPTED);

    }

    // search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
        List<PostDto> results = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }


    // post's image upload
    @PostMapping("posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image, @PathVariable Integer postId) throws IOException {
        PostDto postDto1 = this.postService.getPostById(postId);

        String fileName = this.fileservice.uploadImage(path, image);// path is coming from variable written in starting
        postDto1.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto1, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.ACCEPTED);
    }
}

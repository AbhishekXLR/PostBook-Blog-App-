package com.backendproject.PostBook_App_API.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto  {


    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private UserDto user;

    private CategoryDto category;

//  we didn't add date here because we don't want to show in output. but we are adding explicitly in create method of it's impl

    private List<CommentDto> comments =new ArrayList<>();

}

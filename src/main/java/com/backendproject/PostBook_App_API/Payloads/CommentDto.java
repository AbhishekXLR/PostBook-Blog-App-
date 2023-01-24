package com.backendproject.PostBook_App_API.Payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {

    private int id;

    private String content;

    private UserDto user;
}

package com.backendproject.PostBook_App_API.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String content;


    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;


}

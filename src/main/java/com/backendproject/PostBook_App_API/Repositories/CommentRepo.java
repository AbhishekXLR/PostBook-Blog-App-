package com.backendproject.PostBook_App_API.Repositories;

import com.backendproject.PostBook_App_API.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}

package com.backendproject.PostBook_App_API.Repositories;

import com.backendproject.PostBook_App_API.Entities.Category;
import com.backendproject.PostBook_App_API.Entities.Post;
import com.backendproject.PostBook_App_API.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);  // To get posts of a user
    List<Post> findByCategory(Category category);  // To get posts of a Category

    List<Post> findByTitleContaining(String title);  // operates as a Like query
}

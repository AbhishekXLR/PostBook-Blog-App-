package com.backendproject.PostBook_App_API.Repositories;

import com.backendproject.PostBook_App_API.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {


}

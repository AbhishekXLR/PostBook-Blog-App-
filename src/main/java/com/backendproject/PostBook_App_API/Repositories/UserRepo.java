package com.backendproject.PostBook_App_API.Repositories;

import com.backendproject.PostBook_App_API.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);  // we are taking email as person's Username
}

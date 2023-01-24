package com.backendproject.PostBook_App_API.Repositories;

import com.backendproject.PostBook_App_API.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}

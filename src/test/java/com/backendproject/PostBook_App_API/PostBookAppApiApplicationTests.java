package com.backendproject.PostBook_App_API;

import com.backendproject.PostBook_App_API.Repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostBookAppApiApplicationTests {
    @Autowired
    private UserRepo userRepo;

    @Test
    void contextLoads() {
    }


    @Test
    public void repoTest() {
        String className = this.userRepo.getClass().getName();
        System.out.println(className);
    }

}

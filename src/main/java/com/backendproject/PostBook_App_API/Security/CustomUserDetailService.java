package com.backendproject.PostBook_App_API.Security;

import com.backendproject.PostBook_App_API.Entities.User;
import com.backendproject.PostBook_App_API.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {  // All work is we doing In this class so that our authentication done by taking data from database
                                                                     // UserDetailsService locates the user, based on the username
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from dataBase via his username
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with email :" + username +"not Found!"));

        return user;  // return Type changed, Because we made User implements UserDetailService
    }
}

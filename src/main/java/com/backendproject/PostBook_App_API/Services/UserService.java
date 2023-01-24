package com.backendproject.PostBook_App_API.Services;

import com.backendproject.PostBook_App_API.Payloads.UserRegisterInputDto;
import com.backendproject.PostBook_App_API.Payloads.UserRegisterOutputDto;

import java.util.List;


public interface UserService {


UserRegisterOutputDto registerNewUser(UserRegisterInputDto userDto);
 //UserDto createUser(UserDto user);
 UserRegisterOutputDto  updateUser(UserRegisterInputDto user, Integer userId);
 UserRegisterOutputDto  getUserById(Integer userId);
 List<UserRegisterOutputDto > getAllUsers();
 void deleteUser(Integer userId);
}

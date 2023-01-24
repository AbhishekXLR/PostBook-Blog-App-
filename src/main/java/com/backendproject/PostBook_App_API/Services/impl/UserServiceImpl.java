package com.backendproject.PostBook_App_API.Services.impl;

import com.backendproject.PostBook_App_API.Config.AppConstants;
import com.backendproject.PostBook_App_API.Entities.Role;
import com.backendproject.PostBook_App_API.Entities.User;
import com.backendproject.PostBook_App_API.Exceptions.ResourceNotFoundException;
import com.backendproject.PostBook_App_API.Payloads.UserRegisterInputDto;
import com.backendproject.PostBook_App_API.Payloads.UserRegisterOutputDto;
import com.backendproject.PostBook_App_API.Repositories.RoleRepo;
import com.backendproject.PostBook_App_API.Repositories.UserRepo;
import com.backendproject.PostBook_App_API.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;



    @Override
    public UserRegisterOutputDto registerNewUser(UserRegisterInputDto userInputDto) {
        User user = this.modelMapper.map(userInputDto, User.class);

        //encoding pass
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = this.roleRepo.findById(AppConstants.ROLE_NORMAL).get();
        user.getRoles().add(role);


        User newUser = this.userRepo.save(user);

        return this.modelMapper.map(newUser, UserRegisterOutputDto.class);

    }


    @Override
    public UserRegisterOutputDto updateUser(UserRegisterInputDto userInputDto, Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));

        user.setName(userInputDto.getName());
        user.setEmail(userInputDto.getEmail());
        user.setPassword(this.passwordEncoder.encode(userInputDto.getPassword()));
        user.setAbout(userInputDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser,UserRegisterOutputDto .class);
    }

    @Override
    public UserRegisterOutputDto getUserById(Integer userId) {

       User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

       return this.modelMapper.map(user,UserRegisterOutputDto.class);
    }

    @Override
    public List<UserRegisterOutputDto > getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserRegisterOutputDto > userDto = users.stream().map(user -> this.modelMapper.map(user,UserRegisterOutputDto.class)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(Integer userId) {
       User user= userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
       userRepo.delete(user);

    }


    /* private UserDToToUserConversion(UserDto userDto){
        User user = new User();

              For Update
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDtoConversion(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);

        return userDto;
    }*/
}

package com.backendproject.PostBook_App_API.Controllers;

import com.backendproject.PostBook_App_API.Payloads.UserDto;
import com.backendproject.PostBook_App_API.Payloads.UserRegisterInputDto;
import com.backendproject.PostBook_App_API.Payloads.UserRegisterOutputDto;
import com.backendproject.PostBook_App_API.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

   /* @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt()));  // For encryption & storing password directly in database by providing real password.
        UserDto newUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }*/

    // register new user api
    @PostMapping("/register")
    public ResponseEntity<UserRegisterOutputDto> registerUser(@Valid @RequestBody UserRegisterInputDto userDto){
        UserRegisterOutputDto registeredUser = this.userService.registerNewUser(userDto);

        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserRegisterOutputDto > updateUser(@Valid @RequestBody UserRegisterInputDto userInputDto, @PathVariable Integer userId) {
        UserRegisterOutputDto  updatedUser = this.userService.updateUser(userInputDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    //ADMIN
    @PreAuthorize("hasRole('ROLE_ADMIN')")      // single quotes
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId){
       this.userService.deleteUser(userId);
        return new ResponseEntity(Map.of("message", "User deleted successfully"),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserRegisterOutputDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserRegisterOutputDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}

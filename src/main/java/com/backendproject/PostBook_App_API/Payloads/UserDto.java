package com.backendproject.PostBook_App_API.Payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {           // using it, so that we don't need to use, User directly in the UserService method

   private int id;
   @NotEmpty(message = "Username can't be Empty!")
   @Size(min =4,message = "Username must have minimum of 4 characters !")
   private String name;


   @Email(message = "please provide a valid email !")
   private String email;

   @NotEmpty(message ="Password can't be empty")
   @Size(min =5,max =10,message="your password must contain at least 5 characters and maximum of 10 characters")
   //@Pattern(regexp = )   // for pattern design password
   private String password;

   @NotEmpty(message="You Didn't mention about yourSelf!")
   private String about;

   private Set<RoleDto> roles = new HashSet<>();

}

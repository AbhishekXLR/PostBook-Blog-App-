package com.backendproject.PostBook_App_API.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterInputDto {

    @NotEmpty(message = "Username can't be Empty!")
    @Size(min =4,message = "Username must have minimum of 4 characters !")
    protected String name;

    @Email(message = "please provide a valid email !")
    protected String email;

    @NotEmpty(message ="Password can't be empty")
    @Size(min =5,max =10,message="your password must contain at least 5 characters and maximum of 10 characters")
    //@Pattern(regexp = )   // for pattern design password
    private String password;

    @NotEmpty(message="You Didn't mention about yourSelf!")
    protected String about;


}

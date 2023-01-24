package com.backendproject.PostBook_App_API.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;  // we took email as username
    private String password;

}

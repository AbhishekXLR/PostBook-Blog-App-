package com.backendproject.PostBook_App_API.Payloads;

import lombok.Data;
@Data
public class JwtAuthResponse {

    private String token;
    private String error;
}

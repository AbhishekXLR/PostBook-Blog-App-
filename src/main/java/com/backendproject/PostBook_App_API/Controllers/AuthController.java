package com.backendproject.PostBook_App_API.Controllers;

import com.backendproject.PostBook_App_API.Payloads.JwtAuthRequest;
import com.backendproject.PostBook_App_API.Payloads.JwtAuthResponse;
import com.backendproject.PostBook_App_API.Security.JwtTokenHelper;
import com.backendproject.PostBook_App_API.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {


    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)  {



        JwtAuthResponse response = new JwtAuthResponse();
        try {
            this.authenticate(request.getUsername(), request.getPassword());

            UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());

            String token = this.jwtTokenHelper.generateToken(userDetails);

            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            //throw new UsernamePasswordException("Invalid Username or password");
            response.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        }
    }

    private void authenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);


        this.authenticationManager.authenticate(authenticationToken);


    }

}

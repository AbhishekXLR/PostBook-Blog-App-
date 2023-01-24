package com.backendproject.PostBook_App_API.Exceptions;

public class UsernamePasswordException extends RuntimeException {

    String message;

    public UsernamePasswordException(String message) {
        this.message = message;
        //super(message);
    }

    public UsernamePasswordException() {
    }
}

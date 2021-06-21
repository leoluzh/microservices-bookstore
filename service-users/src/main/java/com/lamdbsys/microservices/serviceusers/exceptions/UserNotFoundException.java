package com.lamdbsys.microservices.serviceusers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String username){
        super(String.format("User with username %s not found.",username));
    }

    public UserNotFoundException(Long id){
        super(String.format("User with id %s not found.",id));
    }

}

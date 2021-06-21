package com.lamdbsys.microservices.serviceusers.controllers;

import com.lamdbsys.microservices.serviceusers.dtos.CredentialsDto;
import com.lamdbsys.microservices.serviceusers.dtos.UserDto;
import com.lamdbsys.microservices.serviceusers.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<UserDto> signin(@RequestBody @Valid final CredentialsDto credentialsDto) {
        log.info("Trying to login with username {}", credentialsDto.getUsername());
        return ResponseEntity.ok(userService.signin(credentialsDto));
    }

    @PostMapping("/validatetoken")
    public ResponseEntity<UserDto> validatetoken(@RequestParam String token) {
        log.info("Trying to validate token {}", token);
        return ResponseEntity.ok(this.userService.validateToken(token));
    }

}

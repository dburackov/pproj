package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.user.UserDto;
import com.dburackov.petfiesta.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody UserDto userDto) {
        var authenticationResponse = authenticationService.signIn(userDto.getEmail(), userDto.getPassword());

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserDto userDto) {
        var authenticationResponse = authenticationService.signUp(userDto);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
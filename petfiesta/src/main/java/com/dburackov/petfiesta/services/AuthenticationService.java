package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.responses.AuthenticationResponse;
import com.dburackov.petfiesta.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UserService userService,
                                 JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse signIn(String email, String password) {
        var user = userService.getUserByEmailAndPassword(email, password);
        var token = jwtUtil.generateToken(user.getId());

        return new AuthenticationResponse(user.getId(), token);
    }

    public AuthenticationResponse signUp(UserDto userDto) {
        var user = userService.createUser(userDto);
        var token = jwtUtil.generateToken(user.getId());

        return new AuthenticationResponse(user.getId(), token);
    }
}
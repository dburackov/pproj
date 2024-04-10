package com.dburackov.petfiesta.controllers;


import com.dburackov.petfiesta.dto.user.UserDto;
import com.dburackov.petfiesta.dto.user.GetUserDto;
import com.dburackov.petfiesta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<GetUserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public GetUserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users/create")
    public GetUserDto createUser (@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/users/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public GetUserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto, Principal principal) {
        System.out.println(principal.getName());
        return userService.updateUser(id, userDto, Long.parseLong(principal.getName()));
    }


    @DeleteMapping("/users/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteUserById(@PathVariable Long id, Principal principal) {
        userService.deleteUserById(id, Long.parseLong(principal.getName()));
    }

}

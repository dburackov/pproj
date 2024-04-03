package com.dburackov.petfiesta.controllers;


import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.dto.userdto.GetUserDto;
import com.dburackov.petfiesta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public GetUserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }


    @DeleteMapping("/users/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}

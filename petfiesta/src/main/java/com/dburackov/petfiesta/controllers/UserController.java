package com.dburackov.petfiesta.controllers;


import com.dburackov.petfiesta.dto.userdto.CreateUserDto;
import com.dburackov.petfiesta.dto.userdto.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<GetUserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users/create")
    public GetUserDto createUser (@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @PostMapping("/users/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }


    @DeleteMapping("/users/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}

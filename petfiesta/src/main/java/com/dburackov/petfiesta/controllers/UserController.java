package com.dburackov.petfiesta.controllers;


import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/users/update/{id}")
    public void updateUser(@PathVariable UUID id, @RequestBody User user) {
        userService.updateUser(id, user);
    }


    @DeleteMapping("/users/delete/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

}

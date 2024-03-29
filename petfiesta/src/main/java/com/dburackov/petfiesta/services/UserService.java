package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {
        //
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User updateUser(Long id, User user) {
        user.setUserId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}

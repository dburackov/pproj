package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.dto.userdto.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.mappers.UserMapper;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<GetUserDto> getUsers() {

        return userRepository.findAll().stream().map(userMapper::userToGetUserDto).toList();
    }

    public GetUserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        
        return userMapper.userToGetUserDto(userRepository.save(user));
    }

    public GetUserDto getUserById(Long id) {
        return userMapper.userToGetUserDto(userRepository.findById(id).get());
    }

    public GetUserDto updateUser(Long id, UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setId(id);
        return userMapper.userToGetUserDto(userRepository.save(user));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).get();
    }

}

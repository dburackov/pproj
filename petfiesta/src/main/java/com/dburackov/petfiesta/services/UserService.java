package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.dto.userdto.CreateUserDto;
import com.dburackov.petfiesta.dto.userdto.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.mappers.UserMapper;
import com.dburackov.petfiesta.repositories.UserRepository;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<GetUserDto> getUsers() {

        return userRepository.findAll().stream().map(userMapper::userToGetUserDto).toList();
    }

    public User createUser(CreateUserDto createUserDto) {
        User user = userMapper.createUserDtoToUser(createUserDto);

        
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}

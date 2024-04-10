package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.dto.user.UserDto;
import com.dburackov.petfiesta.dto.user.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.mappers.UserMapper;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

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

    public GetUserDto updateUser(Long id, UserDto userDto, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(id)) {
            throw new AccessDeniedException("");
        }
        User user = userMapper.userDtoToUser(userDto);
        user.setId(id);
        return userMapper.userToGetUserDto(userRepository.save(user));
    }

    public void deleteUserById(Long id, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(id)) {
            throw new AccessDeniedException("");
        }
        userRepository.deleteById(id);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        var userOpt = userRepository.findByEmailAndPassword(email, password);
        User user = null;
        if (userOpt.isPresent()) {
            user = userOpt.get();
        }
        return user;
    }

}

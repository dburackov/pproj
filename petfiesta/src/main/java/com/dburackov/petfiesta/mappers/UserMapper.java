package com.dburackov.petfiesta.mappers;


import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.dto.userdto.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Mapper(componentModel = "spring")
@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;


    public UserDto userToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User userDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);

    }

    public GetUserDto userToGetUserDto(User user) {
        return modelMapper.map(user, GetUserDto.class);
    }
//    GetUserDto userToGetUserDto(User user);
//
//    User userDtoToUser(UserDto userDto);

}

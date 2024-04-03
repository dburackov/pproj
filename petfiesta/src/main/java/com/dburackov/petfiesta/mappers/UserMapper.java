package com.dburackov.petfiesta.mappers;


import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.dto.userdto.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper{
    GetUserDto userToGetUserDto(User user);

    User userDtoToUser(UserDto userDto);

}
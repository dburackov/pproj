package com.dburackov.petfiesta.mappers;


import com.dburackov.petfiesta.dto.user.UserDto;
import com.dburackov.petfiesta.dto.user.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper{
    GetUserDto userToGetUserDto(User user);

    User userDtoToUser(UserDto userDto);

}
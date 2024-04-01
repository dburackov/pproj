package com.dburackov.petfiesta.mappers;


import com.dburackov.petfiesta.dto.userdto.CreateUserDto;
import com.dburackov.petfiesta.dto.userdto.GetUserDto;
import com.dburackov.petfiesta.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    GetUserDto userToGetUserDto(User user);

    User createUserDtoToUser(CreateUserDto createUserDto);
}

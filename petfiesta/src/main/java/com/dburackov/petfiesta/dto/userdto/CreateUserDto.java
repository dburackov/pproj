package com.dburackov.petfiesta.dto.userdto;


import lombok.Data;

@Data
public class CreateUserDto {
    private String name;
    private String email;
    private String password;
}

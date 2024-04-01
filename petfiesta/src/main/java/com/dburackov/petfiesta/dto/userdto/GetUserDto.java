package com.dburackov.petfiesta.dto.userdto;

import lombok.Data;

@Data
public class GetUserDto {
    private Long id;
    private String name;
    private String email;
}

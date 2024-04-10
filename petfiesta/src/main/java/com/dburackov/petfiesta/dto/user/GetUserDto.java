package com.dburackov.petfiesta.dto.user;

import lombok.Data;

@Data
public class GetUserDto {
    private Long id;
    private String name;
    private String email;
}

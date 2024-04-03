package com.dburackov.petfiesta.dto.petprofiledto;

import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.entities.Photo;
import com.dburackov.petfiesta.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class PetProfileDto {
    private String purpose;
    private User user;
}

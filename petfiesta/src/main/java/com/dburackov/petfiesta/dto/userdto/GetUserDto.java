package com.dburackov.petfiesta.dto.userdto;

import com.dburackov.petfiesta.dto.petprofiledto.PetProfileDto;
import com.dburackov.petfiesta.entities.PetProfile;
import lombok.Data;

import java.util.List;

@Data
public class GetUserDto {
    private Long id;
    private String name;
    private String email;
    private List<PetProfile> petProfiles;
}
